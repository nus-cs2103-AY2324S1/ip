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
        Task taskToMark = tasks.get(index);
        taskToMark.markAsDone();
        tasks.set(index, taskToMark);
        return taskToMark;
    }

    /**
     * Marks a task at a specific index of the list as undone.
     *
     * @param index The index of a task on the list
     * @return The task that is being marked as undone
     */
    public Task unmark(int index) {
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.markAsUndone();
        tasks.set(index, taskToUnmark);
        return taskToUnmark;
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
        Task removedTask = tasks.remove(index);
        return removedTask;
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
