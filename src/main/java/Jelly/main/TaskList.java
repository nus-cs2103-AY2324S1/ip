package Jelly.main;

import java.util.ArrayList;

import Jelly.task.Task;

/**
 * Contains all the commands regarding the list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * Constructor for a new empty tasklist.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Constructor for a tasklist with existing tasks.
     *
     * @param taskList
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the total number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Adds a specified task into the list.
     *
     * @param task The task to be added into the list.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Removes the task at the specified index in the list.
     *
     * @param index The index of the task to be removed.
     */
    public void delete(int index) {
        taskList.remove(index);
    }

    /**
     * Retrieves the task at the specified index in the list.
     *
     * @param index The index of the task to be retrieved.
     * @return The task that was retrieved.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Getter for the list of tasks.
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Marks a task at the specified index of the list, as done.
     *
     * @param index The index of the task to be marked as done.
     */
    public void markAsDone(int index) {
        taskList.get(index).markAsDone();
    }

    /**
     * Marks a task at the specified index of the list, as not done.
     *
     * @param index The index of the task to be marked as not done.
     */
    public void markAsUndone(int index) {
        taskList.get(index).markAsUndone();
    }

    /**
     * Filters the list of tasks to correspond to tasks that match the keyword.
     *
     * @param keyword The keyword to search for.
     * @return The arraylist of tasks that contains the keyword specified.
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();

        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i).getDescription().contains(keyword)) {
                matchingTasks.add(taskList.get(i));
            }
        }
        return matchingTasks;
    }
}
