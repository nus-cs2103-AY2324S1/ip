package jarvis.tasks;

import java.util.ArrayList;

/**
 * Represents a list of tasks in the Jarvis application.
 * Provides methods for managing and retrieving tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Constructs a new TaskList with an empty list of tasks.
     */
    public TaskList() {
        taskList = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Sets the tasks in the task list to the provided list of tasks.
     *
     * @param tasks The list of tasks to set in the task list.
     */
    public void setTasks(ArrayList<Task> tasks) {
        taskList.clear();
        taskList.addAll(tasks);
    }

    /**
     * Gets the list of tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }
    
    /**
     * Gets the task at the specified index in the task list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }

    /**
     * Deletes the task at the specified index in the task list.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int i) {
        Task removedTask = taskList.get(i);
        taskList.remove(i);
        return removedTask;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getTaskCount() {
        return taskList.size();
    }

    /**
     * Checks if the task list is empty.
     *
     * @return true if the task list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return taskList.isEmpty();
    }
}
