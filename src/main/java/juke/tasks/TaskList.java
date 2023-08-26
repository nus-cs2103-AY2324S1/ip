package main.java.juke.tasks;

import main.java.juke.exceptions.arguments.JukeIllegalArgumentException;
import main.java.juke.exceptions.storage.JukeStorageException;
import main.java.juke.exceptions.JukeException;
import main.java.juke.core.JukeObject;
import main.java.juke.storage.Storage;

import java.util.LinkedList;

/**
 * A manager of JukeTasks. This class handles the addition/deletion/manipulation of
 * other child JukeTasks subsumed under its control.
 */
public class TaskList extends JukeObject {
    /** Header for Task Manager String representation. */
    private static final String HEADER = "\n\t>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> TASK LIST <<<<<<<<<<<<<<" +
            "<<<<<<<<<<<<<<<<<<<<<\n";

    /** String representation of the Task Manager when it is empty. */
    private static final String EMPTY = "\t\t\t\t\t\t\t\t\t!No Tasks Present!";

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
        this.tasks = new LinkedList<>(storageManager.get());
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
     * @throws {@code JukeIllegalArgumentException} if there the input argument is invalid
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
     * @throws {@code JukeIllegalArgumentException} if there the input argument is invalid, and
     * {@code JukeIllegalArgumentException} if the user tries to mark a completed task as completed again, or
     * {@code JukeStorageException} if there is an issue with storing the changes
     */
    public void markAsDone(int index) throws JukeStorageException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeIllegalArgumentException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).markAsComplete();
        this.storageManager.write(this.tasks);
    }

    /**
     * Marks a task as incomplete.
     * @param index Index of task to act on.
     * {@code JukeIllegalArgumentException} if the user tries to mark an incomplete task as incompleted again, or
     * {@code JukeStorageException} if there is an issue with storing the changes
     */
    public void markAsUndone(int index) throws JukeStorageException {
        if (index < 0 || index > this.tasks.size()) {
            throw new JukeException("Oh no! I do not have such task recorded!");
        }

        this.tasks.get(index).markAsIncomplete();
        this.storageManager.write(this.tasks);
    }

    /**
     * Returns information of the task at the specified index.
     * @param index Index of task to act on.
     * @return String representation of the task.
     */
    public String taskInformation(int index) {
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
