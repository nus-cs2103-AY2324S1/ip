package bert.tasks;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a task list, which manages a list of tasks and
 * provides functionality for performing operations on the list of tasks.
 */
public class TaskList {
    private List<Task> tasks;

    /**
     * Constructs a TaskList instance containing the list of tasks inputted.
     *
     * @param tasks a list of tasks input
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs a TaskList instance containing an empty list of tasks.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a task at a specific index of the list as done.
     *
     * @param index the index of a task on the list
     * @return the task that is marked as done
     */
    public Task mark(int index) {
        Task t = tasks.get(index);
        t.markAsDone();
        tasks.set(index, t);
        return t;
    }

    /**
     * Marks a task at a specific index of the list as undone.
     *
     * @param index the index of a task on the list
     * @return the task that is marked as undone
     */
    public Task unmark(int index) {
        Task t = tasks.get(index);
        t.markAsUndone();
        tasks.set(index, t);
        return t;
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param toAdd the task to be added
     */
    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    /**
     * Deletes the task at a specific index of the list.
     *
     * @param index the index of a task on the list
     * @return the task that is deleted
     */
    public Task delete(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    /**
     * Returns the size of the list of tasks contained in this TaskList instance.
     *
     * @return the size of the list of tasks
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Converts the task list into its save format.
     *
     * @return the string representation of the save format of the task list
     */
    public String toSaveFormat() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toSaveFormat());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Task t : tasks) {
            sb.append(t.toString());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
