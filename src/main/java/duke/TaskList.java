package duke;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import duke.task.Task;

/**
 * Represents a list of Task objects.
 */
public class TaskList implements Iterable<Task> {

    /**
     * The list of tasks.
     */
    private final List<Task> tasks;

    /**
     * Constructs a new empty TaskList object.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a new TaskList object with the given list of tasks.
     *
     * @param tasks The list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to add.
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes a task by its index.
     *
     * @param index The index of the task to delete.
     * @return The deleted Task.
     */
    public Task delete(int index) {
        return this.tasks.remove(index - 1);
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to mark.
     * @return The marked Task.
     */
    public Task mark(int index) {
        Task task = this.tasks.get(index - 1);
        task.markDone();
        return task;
    }

    /**
     * Unmarks a task as done by its index.
     *
     * @param index The index of the task to unmark.
     * @return The unmarked Task.
     */
    public Task unmark(int index) {
        Task task = this.tasks.get(index - 1);
        task.markUndone();
        return task;
    }

    /**
     * Finds tasks by keyword.
     *
     * @param keyword The keyword to search for.
     * @return The list of tasks found.
     */
    public TaskList findTasksByKeyword(String keyword) {
        ArrayList<Task> foundTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.add(task);
            }
        }
        return new TaskList(foundTasks);
    }

    /**
     * Gets the size of the task list.
     *
     * @return The number of tasks in the list.
     */
    public int getListSize() {
        return this.tasks.size();
    }

    /**
     * Provides an iterator for the task list.
     *
     * @return An iterator over the tasks in the list.
     */
    @Override
    public Iterator<Task> iterator() {
        return this.tasks.iterator();
    }
}
