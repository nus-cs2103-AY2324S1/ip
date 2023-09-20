package juke.tasks;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import juke.commons.classes.JukeObject;
import juke.commons.enums.SortOrderEnum;
import juke.commons.enums.SortTypeEnum;
import juke.commons.exceptions.JukeStateException;
import juke.commons.exceptions.arguments.JukeIllegalArgumentException;
import juke.commons.exceptions.storage.JukeStorageException;
import juke.storage.Storage;

/**
 * Manages all {@code JukeTask}s. This class handles the addition/deletion/manipulation of
 * any {@code JukeTask}s that are created by the user.
 */
public class TaskList extends JukeObject {
    /** Header for {@code TaskList} String representation. */
    private static final String TASK_LIST_HEADER = "Here's your list of tasks:\n\n";

    /** String representation of the {@code TaskList} when it is empty. */
    private static final String NO_TASKS_PRESENT_STRING = "No Tasks Present!";

    /** List of JukeTasks under this {@code TaskList}'s control. */
    private final LinkedList<JukeTask> tasks;

    /**
     * {@code Storage} instance in charge of storing, retrieving and modifying data.
     */
    private final Storage storage;

    /**
     * Creates an instance of {@code TaskList} with all tasks loaded from the datafile.
     *
     * @param storage The storage object that manages any I/O operations on the datafile
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    private TaskList(Storage storage) {
        this.storage = storage;
        this.tasks = new LinkedList<>(storage.read());
    }

    /**
     * Creates an instance of {@code TaskList} from existing tasks.
     *
     * @return {@code TaskList} object
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    public static TaskList of(Storage storageManager) {
        return new TaskList(storageManager);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task {@code JukeTask} object.
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    public void addTask(JukeTask task) {
        int lengthOfTasks = this.tasks.size();

        if (this.tasks.add(task)) {
            assert this.tasks.size() == lengthOfTasks + 1;
            this.storage.write(this.tasks);
        }
    }

    /**
     * Deletes a task by index from the task list.
     *
     * @param task Index of {@code JukeTask} object
     * @throws JukeIllegalArgumentException if the input argument is invalid
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    public void deleteTask(int task) {
        try {
            JukeTask taskToDelete = this.tasks.get(task);
            int lengthOfTasks = this.tasks.size();

            if (this.tasks.remove(taskToDelete)) {
                assert this.tasks.size() == lengthOfTasks - 1;
                this.storage.write(this.tasks);
            }
        } catch (IndexOutOfBoundsException ex) {
            throw new JukeIllegalArgumentException("Oh no! The task index you have provided is not valid!");
        }
    }

    /**
     * Marks a task in the task list as complete.
     *
     * @param index Index of task to act on.
     * @throws JukeIllegalArgumentException if the input argument is invalid
     * @throws JukeStateException if the user tries to mark a completed task as completed again
     * @throws JukeStorageException if there is an issue with storing the changes
     */
    public void setAsComplete(int index) {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).setAsComplete();
        this.storage.write(this.tasks);
    }

    /**
     * Marks a task as incomplete.
     *
     * @param index Index of task to act on.
     * @throws JukeIllegalArgumentException if the input argument is invalid
     * @throws JukeStateException if the user tries to mark an incomplete task as incompleted again
     * @throws JukeStorageException if there is an issue with storing the changes
     */
    public void setAsIncomplete(int index) throws JukeStorageException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).setAsIncomplete();
        this.storage.write(this.tasks);
    }

    /**
     * Returns information of the task at the input index.
     *
     * @param index Index of task to act on.
     * @return String representation of the task.
     * @throws JukeIllegalArgumentException if the input argument is invalid
     */
    public String getTaskInformation(int index) {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        return this.tasks.get(index).toString();
    }

    /**
     * Finds all tasks that match the specified word.
     *
     * @param word Word of interest
     * @return {@code List} of {@code JukeTask} objects whose subject matches the word
     */
    public List<JukeTask> findTask(String word) {
        List<JukeTask> wordMatches = new LinkedList<>();

        for (JukeTask t : this.tasks) {
            if (t.stringMatches(word)) {
                wordMatches.add(t);
            }
        }

        return wordMatches;
    }

    /**
     * Sorts the tasks in the {@code TaskList} by the input {@code SortOrderEnum} and {@code SortTypeEnum}.
     *
     * @param sortOrder the order to sort the tasks by
     * @param sortType the type of sort to perform on the tasks
     */
    public final void sort(SortOrderEnum sortOrder, SortTypeEnum sortType) {
        // stores the original copy of the tasks for error recovery
        List<JukeTask> originalTasks = new LinkedList<>(this.tasks);

        try {
            this.tasks.sort((t1, t2) -> t2.sortBy(t1, sortType));

            if (sortOrder.equals(SortOrderEnum.DESCENDING)) {
                this.reverse();
            }
        } catch (IllegalArgumentException | UnsupportedOperationException | ClassCastException ex) {
            // if there is an error, revert the changes
            this.tasks.clear();
            this.tasks.addAll(originalTasks);
            throw new JukeIllegalArgumentException("Oh no! I cannot sort the list!");
        } finally {
            // save any changes made to the task list
            this.storage.write(this.tasks);
        }
    }

    /**
     * Reverses the order of the task list.
     */
    private void reverse() {
        Collections.reverse(this.tasks);
    }

    /**
     * Returns String representation of the {@code TaskList}, which contains all tasks present
     * within the task list.
     *
     * @return String representation of this {@code TaskList}.
     */
    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return TaskList.NO_TASKS_PRESENT_STRING;
        }

        StringBuilder builder = new StringBuilder();
        builder.append(TaskList.TASK_LIST_HEADER);

        for (int i = 0; i < this.tasks.size(); i++) {
            builder.append(i + 1)
                   .append(". ")
                   .append(this.tasks.get(i))
                   .append("\n");
        }

        return builder.toString().strip();
    }
}
