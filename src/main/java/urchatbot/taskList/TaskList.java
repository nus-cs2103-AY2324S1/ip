package urchatbot.taskList;
import java.util.ArrayList;

import urchatbot.tasks.Task;


/**
 * Represents a list of tasks input by the user.
 */
public class TaskList {
    private static ArrayList<Task> tasks;

    /**
     * Constructs the Tasklist class.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Constructs the Tasklist class in an alternative way.
     *
     * @param tasks Task to construct the tasklist.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds tasks to the tasklist.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
        assert tasks.size() > 0 : "Number of tasks should be more than zero!";
    }

    /**
     * Deletes tasks to the tasklist.
     *
     * @param index Task number to delete.
     */
    public void deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.remove(index);
        }
        assert tasks.size() > -1 : "Number of tasks should be more than -1!";
    }

    /**
     * Clears all tasks in the tasklist.
     */
    public void clearTask() {
        tasks.clear();
        assert tasks.size() == 0 : "Number of tasks should be zero!";
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    public int getSize() {
        return tasks.size();
    }
}
