package duke.task;

import java.util.ArrayList;

import duke.DukeException;
import duke.Storage;
import duke.ui.Ui;
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
     * @return This method returns the string to show that the task has been added.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        db.saveTask(tasks);
        return Ui.addTask(task, this.tasks.size());
    }

    /**
     * Deletes a Task object from the specified list of tasks.
     * @param taskNumber The index of the task to be deleted.
     * @return This method returns the string to show that the task has been deleted.
     */
    public String deleteTask(int taskNumber) {
        if (taskNumber > tasks.size() || taskNumber < 1) {
            return Ui.returnErrorString(new DukeException("Please enter a valid task number."));
        }
        Task cur = this.tasks.get(taskNumber - 1);
        this.tasks.remove(taskNumber - 1);
        db.saveTask(tasks);
        return Ui.deleteTask(cur, this.tasks.size());
    }

    /**
     * Returns the list of tasks.
     * @return This method returns a list of the tasks.
     */
    public String listAllTasks() {
        return Ui.listAllTasks(tasks);
    }

    /**
     * Returns the list of tasks that contain the specified keyword.
     * @param taskNumber The keyword to be searched.
     * @return This method returns the string after marking the task.
     */
    public String markTaskAsDone(int taskNumber) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            return Ui.returnErrorString(new DukeException("Please enter a valid task number."));
        }

        Task tsk = this.tasks.get(taskNumber - 1);
        tsk.markAsDone();
        db.saveTask(tasks);
        return Ui.markAsDone(tsk);
    }

    /**
     * Returns the list of tasks that contain the specified keyword.
     * @param taskNumber The keyword to be searched.
     * @return This method returns the string after un-marking the task.
     */
    public String markTaskAsUndone(int taskNumber) {
        if (taskNumber > this.tasks.size() || taskNumber < 1) {
            return Ui.returnErrorString(new DukeException("Please enter a valid task number."));
        }
        Task tsk = this.tasks.get(taskNumber - 1);
        tsk.markAsUndone();
        db.saveTask(tasks);
        return Ui.markAsUndone(tsk);
    }

    /**
     * Returns the list of tasks that contain the specified keyword.
     * @param keyword The keyword to be searched.
     * @return This method returns the string to find the task with the keyword.
     */
    public String findTasks(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<Task>();
        for (Task tsk : this.tasks) {
            if (tsk.getDescription().contains(keyword)) {
                foundTasks.add(tsk);
            }
        }
        return Ui.findTasks(foundTasks);
    }
}
