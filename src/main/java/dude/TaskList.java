package dude;

import dude.task.Task;

import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    // getters
    public int getSize() {
        return this.taskList.size();
    }

    public Task getTask(int index) {
        return this.taskList.get(index);
    }

    private ArrayList getAllTasks() {
        return this.taskList;
    }

    /**
     * Marks a task as done.
     *
     * @param index Index of the task to be marked as done.
     */
    public Task markTask(int index) {
        Task task = taskList.get(index);
        task.setDone(true);
        return task;
    }

    /**
     * Marks a task as undone.
     *
     * @param index Index of the task to be marked as undone.
     */
    public Task unmarkTask(int index) {
        Task task = taskList.get(index);
        task.setDone(false);
        return task;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Removes a task from the list.
     *
     * @param index Index of the task to be removed from the list.
     */
    public Task deleteTask(int index) {
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        return removedTask;
    }

    public TaskList findTasks(String taskKeywords) {
        TaskList searchResults = new TaskList();
        for (int i = 0; i < this.getSize(); i++) {
            Task task = this.getTask(i);
            if (task.containKeywords(taskKeywords)) {
                searchResults.addTask(task);
            }
        }
        return searchResults;
    }
}
