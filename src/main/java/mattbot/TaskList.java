package mattbot;

import java.util.ArrayList;

import mattbot.task.Task;

/**
 * Handles a list of Tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a new empty Task List.
     */
    public TaskList() {
        tasks = new ArrayList<Task>();
    }
    /**
     * Adds new task to list.
     *
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }
    /**
     * Removes task specified by index from list.
     *
     * @param idx 1-based index of task to be removed.
     */
    public void removeTask(int idx) {
        tasks.remove(idx - 1);
    }

    /**
     * Marks task as done.
     *
     * @param idx 1-based index of task to be marked as complete.
     */
    public void markTask(int idx) {
        Task t = tasks.get(idx - 1);
        t.markAsDone();
    }
    /**
     * Marks task as not done.
     *
     * @param idx 1-based index of task to be marked as incomplete.
     */
    public void unmarkTask(int idx) {
        Task t = tasks.get(idx - 1);
        t.markAsNotDone();
    }

    /**
     * Returns size of task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns entire task list.
     *
     * @return List of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a task in the TaskList as specified by a 1-based index.
     *
     * @return Task as specified by a 1-based index.
     */
    public Task getTask(int idx) {
        return tasks.get(idx - 1);
    }
}
