package dude.task;

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
        assert task.isDone() : "Marked task should be done.";
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
        assert !task.isDone() : "Unmarked task should not be done.";
        return task;
    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        int oldTaskListLength = this.getSize();
        taskList.add(task);
        int newTaskListLength = this.getSize();
        assert newTaskListLength == oldTaskListLength + 1
                : "Length of task list after adding task should increase by length 1.";
    }

    /**
     * Removes a task from the list.
     *
     * @param index Index of the task to be removed from the list.
     */
    public Task deleteTask(int index) {
        int oldTaskListLength = this.getSize();
        Task removedTask = taskList.get(index);
        taskList.remove(index);
        int newTaskListLength = this.getSize();
        assert newTaskListLength == oldTaskListLength - 1
                : "Length of task list after deleting task should decrease by length 1.";
        return removedTask;
    }

    /**
     * Finds tasks in list that contain specified keyword(s).
     *
     * @param taskKeywords Keywords used to search for tasks.
     */
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
