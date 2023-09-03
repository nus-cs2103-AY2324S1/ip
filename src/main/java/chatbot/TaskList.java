package chatbot;

import java.util.ArrayList;
import java.util.List;

/**
 * Stores and handles the addition/deletion list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    /**
     * The task is added to the list.
     * 
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * The task is removed from the list.
     * 
     * @param index The index of the task to be removed.
     */
    public void delete(int index) {
        tasks.remove(index);
    }

    /**
     * The task is marked and returned back.
     * 
     * @param index The index of the task to be marked.
     * @return The task that was marked.
     */
    public Task mark(int index) {
        Task task = tasks.get(index);
        task.markTaskDone();
        return task;
    }

    /**
     * The task is unmarked and returned back.
     * 
     * @param index The index of the task to be unmarked.
     * @return The task that was unmarked.
     */
    public Task unmark(int index) {
        Task task = tasks.get(index);
        task.markTaskNotDone();
        return task;
    }

    /**
     * Retrieves the task in the list.
     * 
     * @param index The index of the task.
     * @return The task in the corresponding index.
     */
    public Task retrieveTask(int index) {
        return tasks.get(index);
    }

    /**
     * Returns the size of the list
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Returns the task list
     */
    public List<Task> getTaskList() {
        return tasks;
    }

    /**
     * Retrieves the filtered list according to the keyword.
     * 
     * @param keyword The word to filter the list.
     * @return The filtered list.
     */
    public List<Task> filterByWord(String keyword) {
        List<Task> filteredList = new ArrayList<Task>();
        for (Task task : tasks) {
            if (task.description.contains(keyword)) {
                filteredList.add(task);
            }
        }
        return filteredList;
    }
}