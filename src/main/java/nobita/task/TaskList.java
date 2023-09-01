package nobita.task;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class that encapsulates TaskList.
 * TaskList contains all tasks in an ArrayList.
 *
 * @author Zheng Chenglong
 */
public class TaskList implements Iterable<Task> {

    /** The list that contains all tasks */
    ArrayList<Task> tasks;

    /**
     * Constructs TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Getter for number of tasks.
     * @return An integer representing number of total tasks.
     */
    public int getTotalTask() {
        return this.tasks.size();
    }

    /**
     * Add a task to the list.
     * @param task The task to be added to list.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Delete a task from list with given index.
     *
     * @param targetTask The index of task to be deleted.
     * @return The deleted Task.
     */
    public Task deleteTask(int targetTask) {
        return this.tasks.remove(targetTask);
    }

    /**
     * Mark a task to be complete
     *
     * @param targetTask The index of the task to be mark complete.
     * @return The Task that is mark complete.
     */
    public Task markComplete(int targetTask) {
        Task task = this.tasks.get(targetTask);
        task.markComplete();
        return task;
    }

    /**
     * Mark a task to be incomplete
     *
     * @param targetTask The index of the task to be mark incomplete.
     * @return The Task that is mark incomplete.
     */
     public Task markIncomplete(int targetTask) {
        Task task = this.tasks.get(targetTask);
        task.markIncomplete();
        return task;
    }

    /**
     * Rrturn all tasks in ArrayList.
     * @return An ArrayList that contains all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Print all tasks that matches the query.
     * @param query The query to compare with all tasks name.
     *
     * @Return A TaskList containing all match tasks.
     */
    public TaskList findTask(String query) {
        TaskList matchedQuery = new TaskList();
        for (Task task: tasks) {
            if (task.getTaskName().toLowerCase().contains(query.toLowerCase())) {
                matchedQuery.addTask(task);
            }
        }
        return matchedQuery;
    }

    /**
     * Check if a task index is within the tasklist.
     * @param ind The task index to be checked.
     * @return True if task index fall within the tasklist, False otherwise.
     */
    public boolean checkIndexWithinRange(int ind) {
        return ind < 1 || ind > this.tasks.size();
    }

    /**
     * Return the Iterator for TaskList
     *
     * @return A Task Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}

