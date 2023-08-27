package juke.tasks;

import java.util.LinkedList;

import juke.core.JukeObject;
import juke.exceptions.JukeStateException;
import juke.exceptions.arguments.JukeIllegalArgumentException;
import juke.exceptions.storage.JukeStorageException;
import juke.storage.Storage;

/**
 * Manages {@code JukeTasks}. This class handles the addition/deletion/manipulation of
 * other child {@code JukeTasks} subsumed under its control.
 */
public class TaskList extends JukeObject {
    /** Header for {@code TaskList} String representation. */
    private static final String HEADER = "\n\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TASK LIST <<<<<<<<<<<<<<"
            + "<<<<<<<<<<<<<<<<<<<<<\n";

    /** String representation of the {@code TaskList} when it is empty. */
    private static final String EMPTY = "\t\t\t\t\t\t\t\t\t!No Tasks Present!";

    /** List of JukeTasks under this {@code TaskList}'s control. */
    private final LinkedList<JukeTask> tasks;

    /**
     * {@code Storage} instance in charge of storing, retrieving and modifying data.
     */
    private final Storage storageManager;

    /**
     * Private constructor for TaskList that initialises the tasks within this {@code TaskList}.
     * @return {@code TaskList} object that is properly constructed
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    private TaskList(Storage storageManager) {
        this.storageManager = storageManager;
        this.tasks = new LinkedList<>(storageManager.get());
    }

    /**
     * Factory method to create a {@code TaskList}, from existing saved tasks.
     * @return {@code TaskList} object
     * @throws JukeStorageException if there is are any issues with retrieving data from the datafile
     */
    public static TaskList of(Storage storageManager) {
        return new TaskList(storageManager);
    }

    /**
     * Adds a task.
     * @param task JukeTask object.
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
     * @param task Index of JukeTask object
     * @return true if the task is successfuly deleted, else false
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
     * Marks a task as complete.
     * @param index Index of task to act on.
     * @throws JukeIllegalArgumentException if the input argument is invalid
     * @throws JukeStateException if the user tries to mark a completed task as completed again
     * @throws JukeStorageException if there is an issue with storing the changes
     */
    public void markAsDone(int index) {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).markAsComplete();
        this.storageManager.write(this.tasks);
    }

    /**
     * Marks a task as incomplete.
     * @param index Index of task to act on.
     * @throws JukeIllegalArgumentException if the input argument is invalid
     * @throws JukeStateException if the user tries to mark an incomplete task as incompleted again
     * @throws JukeStorageException if there is an issue with storing the changes
     */
    public void markAsUndone(int index) throws JukeStorageException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).markAsIncomplete();
        this.storageManager.write(this.tasks);
    }

    /**
     * Returns information of the task at the specified index.
     * @param index Index of task to act on.
     * @return String representation of the task.
     * @throws JukeIllegalArgumentException if the input argument is invalid
     */
    public String taskInformation(int index) {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        return this.tasks.get(index).toString();
    }

    /**
     * String representation of the task manager. This consists of all the tasks
     * managed by this task manager.
     * @return String representation of the task manager.
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(TaskList.HEADER);

        if (this.tasks.isEmpty()) {
            builder.append(TaskList.EMPTY);
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
