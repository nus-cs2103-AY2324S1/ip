package aichan;

import java.util.ArrayList;

import aichan.task.Task;


/**
 * Represents a list contains the tasks.
 */
public class TaskList {
    private ArrayList<Task> arrTask;

    /**
     * Constructs a empty list.
     */
    public TaskList() {
        this.arrTask = new ArrayList<>();
    }

    /**
     * Constructs a list contains the given tasks.
     *
     * @param arrTask An ArrayList containing tasks.
     */
    public TaskList(ArrayList<Task> arrTask) {
        this.arrTask = arrTask;
    }

    /**
     * Adds the given task to the list.
     *
     * @param task A Task object to be added.
     */
    public void addTask(Task task) {
        arrTask.add(task);
    }

    /**
     * Deletes task from the list with ID and returns it.
     *
     * @param index Index of the task inside the list.
     * @return The task.
     */
    public Task deleteTask(int index) {
        return arrTask.remove(index - 1);
    }

    /**
     * Gets and returns the task with the ID.
     *
     * @param index Index of the task inside the list.
     * @return The task.
     */
    public Task getTask(int index) {
        return arrTask.get(index - 1);
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The size of the list.
     */
    public int getSize() {
        return arrTask.size();
    }

    /**
     * Gets the list of tasks.
     *
     * @return The ArrayList containing tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.arrTask;
    }

    /**
     * Delete all marked tasks in the list.
     *
     *  @return String contains list of deleted tasks.
     */
    public String deleteMarkedTasks() {
        String markedTasks = "";
        int size = this.getSize();
        int numberOfDeletedTask = 0;
        // taskId here refer to the original Id of the task.
        for (int taskId = 1; taskId <= size; taskId++) {
            // after delete some tasks, we need to minus the numberOfDeletedTask to get a task with its old taskId
            Task t = this.getTask(taskId - numberOfDeletedTask);
            if (t.getIsDone()) {
                markedTasks = markedTasks + taskId + "." + t.toString() + "\n";
                this.deleteTask(taskId - numberOfDeletedTask);
                numberOfDeletedTask++;
            }
        }
        return markedTasks;
    }
}
