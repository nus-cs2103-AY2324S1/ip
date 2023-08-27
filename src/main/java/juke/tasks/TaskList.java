package juke.tasks;

import java.util.LinkedList;
import java.util.List;

import juke.core.JukeObject;
import juke.exceptions.JukeException;
import juke.exceptions.arguments.JukeIllegalArgumentException;
import juke.exceptions.storage.JukeStorageException;
import juke.storage.Storage;

/**
 * A manager of JukeTasks. This class handles the addition/deletion/manipulation of
 * other child JukeTasks subsumed under its control.
 */
public class TaskList extends JukeObject {
    /** Header for Task Manager String representation. */
    private static final String TASK_LIST_HEADER = "\n\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TASK LIST <<<<<<<<<<<<<<"
            + "<<<<<<<<<<<<<<<<<<<<<\n";

    /** String representation of the Task Manager when it is empty. */
    private static final String NO_TASKS_PRESENT_STRING = "\t\t\t\t\t\t\t\t\t!No Tasks Present!";

    /** List of JukeTasks under this Task Manager's control. */
    private final LinkedList<JukeTask> tasks;

    /**
     * {@code JukeStorageManager} instance in charge of storing, retrieving and
     * modifying data.
     */
    private final Storage storageManager;

    /**
     * Private constructor for TaskList that initialises the
     * tasks within this Manager.
     */
    private TaskList(Storage storageManager) throws JukeStorageException {
        this.storageManager = storageManager;
        this.tasks = new LinkedList<>(storageManager.read());
    }

    /**
     * Factory method to create a TaskList, from existing saved tasks.
     * @return TaskList object
     */
    public static TaskList of(Storage storageManager) throws JukeStorageException {
        return new TaskList(storageManager);
    }

    /**
     * Adds a task.
     * @param task JukeTask object.
     * @return true if the task is added, else false
     */
    public boolean addTask(JukeTask task) throws JukeStorageException {
        boolean success = this.tasks.add(task);

        if (success) {
            this.storageManager.write(this.tasks);
        }

        return success;
    }

    /**
     * Deletes a task by index.
     * @param task Index of JukeTask object
     * @return true if the task is successfuly deleted, else false
     * @throws JukeIllegalArgumentException if there the input argument is invalid
     */
    public JukeTask deleteTask(int task) throws JukeStorageException {
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
     * Marks a task as complete.
     * @param index Index of task to act on.
     * @throws JukeIllegalArgumentException if there the input argument is invalid, and
     *     {@code JukeIllegalArgumentException} if the user tries to mark a completed task as completed again, or
     *     {@code JukeStorageException} if there is an issue with storing the changes
     */
    public void setAsComplete(int index) throws JukeStorageException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).setAsComplete();
        this.storageManager.write(this.tasks);
    }

    /**
     * Marks a task as incomplete.
     * @param index Index of task to act on.
     * @throws JukeIllegalArgumentException if the user tries to mark an incomplete task as incompleted again, or
     *     {@code JukeStorageException} if there is an issue with storing the changes
     */
    public void setAsIncomplete(int index) throws JukeStorageException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).setAsIncomplete();
        this.storageManager.write(this.tasks);
    }

    /**
     * Returns information of the task at the specified index.
     * @param index Index of task to act on.
     * @return String representation of the task.
     */
    public String getTaskInformation(int index) {
        return this.tasks.get(index).toString();
    }

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
     * String representation of the task manager. This consists of all the tasks
     * managed by this task manager.
     * @return String representation of the task manager.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(TaskList.TASK_LIST_HEADER);

        if (this.tasks.isEmpty()) {
            builder.append(TaskList.NO_TASKS_PRESENT_STRING);
        } else {
            for (int i = 0; i < this.tasks.size(); i++) {
                builder.append("\t")
                       .append((i + 1) + ". ")
                       .append(this.tasks.get(i))
                       .append("\n");
            }
        }

        return builder.toString();
    }
}
