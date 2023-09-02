package haste.data;

import haste.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a list of Task objects.
 */
public class TaskList {
    public ArrayList<Task> taskList;
    // number tracker
    public static int numOfTasks;

    /**
     * Creates a TaskList to store Task objects.
     */
    public TaskList() {
        taskList = new ArrayList<>();
        numOfTasks = 0;
    }

    /**
     * Adds a Task to the TaskList.
     *
     * @param newTask Task object to be added.
     */
    public void addTask(Task newTask) {
        taskList.add(newTask);
        numOfTasks++;
    }

    /**
     * Deletes a Task in the TaskList.
     * @param id Index of the Task in the ArrayList.
     */
    public void deleteTask(int id) {
        taskList.remove(id);
        numOfTasks--;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return Total Tasks.
     */
    public int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Returns Tasks object with corresponding index.
     *
     * @param id Index of Task in the ArrayList.
     * @return Task Object.
     */
    public Task getTask(int id) {
        return taskList.get(id);
    }
}
