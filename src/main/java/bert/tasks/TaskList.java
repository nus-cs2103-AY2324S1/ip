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

    /**
     * Finds matching tasks based on a given keyword.
     *
     * @param keyword A word used to search for tasks containing this word.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> find(String keyword) {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
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
        for (Task task : tasks) {
            sb.append(task.toSaveFormat());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            sb.append(i + 1).append(".");
            sb.append(task);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
