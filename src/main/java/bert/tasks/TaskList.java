package bert.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks;

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Marks a task at a specific index of the list as done.
     *
     * @param index The index of a task on the list
     * @return The task that is being marked as done
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
     * @param index The index of a task on the list
     * @return The task that is being marked as undone
     */
    public Task unmark(int index) {
        Task t = tasks.get(index);
        t.markAsUndone();
        tasks.set(index, t);
        return t;
    }

    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    /**
     * Deletes the task at the specific index of the list.
     *
     * @param index The index of a task on the list
     * @return The task that is being deleted
     */
    public Task delete(int index) {
        Task t = tasks.remove(index);
        return t;
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Converts the task list into its save format.
     *
     * @return The String representation of the formatted task list
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
