package task;

import java.util.ArrayList;

/**
 * Represents a list of tasks in Thorndike.
 *
 * @author Ho Khee Wei
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<>();
    }

    /**
     * Retrieves the task at the specified index in the list.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return list.get(index);
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to add to the list.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Deletes a task from the TaskList at the specified index.
     *
     * @param index The index of the task to delete.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task deleted = list.get(index);
        list.remove(index);
        return deleted;
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to mark as done.
     */
    public void markDone(int index) {
        list.get(index).setDone();
    }

    /**
     * Marks a task at the specified index as not done.
     *
     * @param index The index of the task to mark as not done.
     */
    public void markNotDone(int index) {
        list.get(index).setNotDone();

    }

    /**
     * Checks if an index is within the valid range of task indices.
     *
     * @param index The index to check.
     * @return true if the index is within the valid range, false otherwise.
     */
    public boolean hasIndex(int index) {
        return index > 0 && index < list.size();
    }

    /**
     * Retrieves the number of tasks in the TaskList.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return list.size();
    }

    /**
     * Searches for tasks with description containing the keyword.
     *
     * @param keyword The keyword to search.
     * @return an array of task with description containing the keyword.
     */
    public Task[] search(String keyword) {
        Object[] tempRes = list.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase())).toArray();
        Task[] res = new Task[tempRes.length];
        for (int i = 0; i < tempRes.length; i++) {
            res[i] = (Task) tempRes[i];
        }
        return res;
    }

}
