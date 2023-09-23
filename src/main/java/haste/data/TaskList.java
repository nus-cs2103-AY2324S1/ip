package haste.data;

import java.util.ArrayList;

import haste.tasks.Task;

/**
 * Represents a list of Task objects.
 */
public class TaskList {
    private static int numOfTasks;
    private ArrayList<Task> taskList;

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

        // id must be more than zero in array
        assert id >= 0 : "id should not be negative";

        taskList.remove(id);
        numOfTasks--;
    }

    /**
     * Returns the total number of tasks.
     *
     * @return Total Tasks.
     */
    public static int getNumOfTasks() {
        return numOfTasks;
    }

    /**
     * Returns Tasks object with corresponding index.
     *
     * @param id Index of Task in the ArrayList.
     * @return Task Object.
     */
    public Task getTask(int id) {
        // id must be more than 0
        assert id >= 0;
        return taskList.get(id);
    }

    /**
     * Returns a new TaskList containing Tasks containing keyword.
     *
     * @param keyword Keyword to search for in Task Descriptions.
     * @return TaskList of filtered Tasks.
     */
    public TaskList filterTasks(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (Task task : this.taskList) {
            if (task.getDescription().contains(keyword)) {
                filteredTasks.addTask(task);
            }
        }
        // change num of tasks back to original task size
        TaskList.numOfTasks = this.taskList.size();

        return filteredTasks;
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }


}
