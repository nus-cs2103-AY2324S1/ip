package juke.tasks;

import java.util.LinkedList;
import java.util.List;

import juke.core.JukeObject;
import juke.exceptions.JukeStateException;
import juke.exceptions.arguments.JukeIllegalArgumentException;
import juke.exceptions.storage.JukeStorageException;
import juke.storage.Storage;
import juke.utils.StringUtils;

/**
 * Manages {@code JukeTasks}. This class handles the addition/deletion/manipulation of
 * other child {@code JukeTasks} subsumed under its control.
 */
public class TaskList extends JukeObject {
    /** Header for {@code TaskList} String representation. */
    private static final String TASK_LIST_HEADER = "Here's your list of tasks:\n\n";

    /** String representation of the {@code TaskList} when it is empty. */
    private static final String NO_TASKS_PRESENT_STRING = "No Tasks Present!";

    /** Max number of characters on a line. */
    private static final int MAX_LINE_LENGTH = 35;

    /** List of JukeTasks under this {@code TaskList}'s control. */
    private final LinkedList<JukeTask> tasks;

    /**
     * {@code Storage} instance in charge of storing, retrieving and modifying data.
     */
    private final Storage storageManager;

    /**
     * Creates an instance of {@code TaskList} that initialises the tasks within
     * this {@code TaskList}.
     *
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    private TaskList(Storage storageManager) {
        this.storageManager = storageManager;
        this.tasks = new LinkedList<>(storageManager.read());
    }

    /**
     * Creates an instance of {@code TaskList}, from existing saved tasks.
     *
     * @return {@code TaskList} object
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    public static TaskList of(Storage storageManager) {
        return new TaskList(storageManager);
    }

    /**
     * Adds a task.
     *
     * @param task {@code JukeTask} object.
     * @return true if the task is added, else false
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    public boolean addTask(JukeTask task) {
        boolean success = this.tasks.add(task);

        if (success) {
            this.storageManager.write(this.tasks);
        }

        return success;
    }

    /**
     * Deletes a task by index.
     *
     * @param task Index of {@code JukeTask} object
     * @return {@code JukeTask} deleted if the task is successfully deleted, else throws an exception
     * @throws JukeIllegalArgumentException if the input argument is invalid
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    public JukeTask deleteTask(int task) {
        try {
            JukeTask retTask = this.tasks.get(task);

            if (this.tasks.remove(retTask)) {
                this.storageManager.write(this.tasks);
            }

            return retTask;
        } catch (IndexOutOfBoundsException ex) {
            throw new JukeIllegalArgumentException("Oh no! The task index you have provided is not valid!");
        }
    }

    /**
     * Sets a task as complete.
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
        this.storageManager.write(this.tasks);
    }

    /**
     * Sets a task as incomplete.
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
        this.storageManager.write(this.tasks);
    }

    /**
     * Returns information of the task at the specified index.
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
        List<JukeTask> matchesWord = new LinkedList<>();

        for (JukeTask t : this.tasks) {
            if (t.stringMatches(word)) {
                matchesWord.add(t);
            }
        }

        return matchesWord;
    }

    /**
     * Returns String representation of the {@code TaskList}. This consists of all the tasks
     * managed by this {@code TaskList}.
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
            String built = (i + 1) + ". " + this.tasks.get(i) + "\n";
            builder.append(StringUtils.wrap(built, TaskList.MAX_LINE_LENGTH));
        }

        return builder.toString().strip();
    }
}
