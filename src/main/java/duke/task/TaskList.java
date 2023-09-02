package duke.task;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
/**
 * Represents a list of tasks.
 */
public class TaskList {
    /** Represents the list of tasks. */
    private ArrayList<Task> tasks;
    /** Represents the database that stores the list of tasks. */
    private Storage db;

    /**
     * Constructs a TaskList object with an empty list of tasks.
     */
    public TaskList() {
        db = new Storage("data/duke.ser");
        this.tasks = db.loadTask();
    }

    /**
     * Adds a Task object to the specified list of tasks.
     * @param task The list of tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        db.saveTask(tasks);
        Ui.addTask(task, this.tasks.size());
    }

    /**
     * Deletes a Task object from the specified list of tasks.
     * @param taskNumber The index of the task to be deleted.
     * @throws DukeException If the task number is invalid.
     */
    public void deleteTask(int taskNumber) throws DukeException {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }

        Ui.deleteTask(this.tasks.get(taskNumber - 1), this.tasks.size());
        this.tasks.remove(taskNumber - 1);
        db.saveTask(tasks);
    }

    /**
     * Returns the list of tasks.
     */
    public void listAllTasks() {
        Ui.lsitAllTasks(tasks);
    }

    /**
     * Marks the task as done.
     * @param taskNumber The task number to be marked as done.
     */
    public void markTaskAsDone(int taskNumber) throws DukeException {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }

        Task tsk = this.tasks.get(taskNumber - 1);
        tsk.markAsDone();
        db.saveTask(tasks);
        Ui.markAsDone(tsk);
    }

    /**
     * Marks the task as not done.
     * @param taskNumber The task number to be marked as not done.
     */
    public void markTaskAsUndone(int taskNumber) throws DukeException {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            throw new DukeException("Please enter a valid task number.");
        }
        Task tsk = this.tasks.get(taskNumber - 1);
        tsk.markAsUndone();
        db.saveTask(tasks);
        Ui.markAsUndone(tsk);
    }

    /**
     * Returns the list of tasks that contain the specified keyword.
     * @param keyword The keyword to be searched.
     */
    public void findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task tsk : this.tasks) {
            if (tsk.getDescription().contains(keyword)) {
                foundTasks.add(tsk);
            }
        }
        Ui.findTasks(foundTasks);
    }
}
