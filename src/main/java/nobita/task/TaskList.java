package nobita.task;

import nobita.exception.NobitaException;

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
     *
     * @return An integer representing number of total tasks.
     */
    public int getTasksSize() {
        return this.tasks.size();
    }

    /**
     * Add a task to the list.
     *
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
     *
     * @return An ArrayList that contains all tasks.
     */
    public ArrayList<Task> getAllTasks() {
        return this.tasks;
    }

    /**
     * Print all tasks that matches the query.
     *
     * @param query The query to compare with all tasks name.
     * @return A TaskList containing all match tasks.
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
     *
     * @param ind The task index to be checked.
     * @return True if task index fall within the tasklist, False otherwise.
     */
    public boolean checkIndexWithinRange(int ind) {
        return ind < 0 || ind >= this.tasks.size();
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

    /**
     * Return a String representation of the entire list.
     *
     * @return A String representation of the entire list.
     */
    @Override
    public String toString() {
        if (this.getTasksSize() < 1) {
            return "There are no tasks to be shown.";
        }
        int listInd = 1;
        StringBuilder tasksStringBuilder = new StringBuilder();
        for (Task task : tasks) {
            tasksStringBuilder.append(String.format("%d. %s\n", listInd, task));
            listInd++;
        }
        return tasksStringBuilder.toString();
    }

    /**
     * Update a task field.
     *
     * @param targetTask The index of the task to update.
     * @param updateField The field of the task to update.
     * @param updateValue The value of the field to update to.
     * @return The Task that is updated.
     * @throws NobitaException If error occur while updating task.
     */
    public Task updateTask(int targetTask, String updateField, String updateValue) throws NobitaException {
        Task task = this.tasks.get(targetTask);
        return task.setField(updateField, updateValue);
    }
}

