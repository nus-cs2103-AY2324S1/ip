package bert.tasks;

import java.util.ArrayList;
import java.util.Comparator;
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
     * @param tasks A list of tasks input.
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
     * @param index The index of a task on the list.
     * @return The task that is marked as done.
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
     * @param index The index of a task on the list.
     * @return The task that is marked as undone.
     */
    public Task unmark(int index) {
        Task taskToUnmark = tasks.get(index);
        taskToUnmark.markAsUndone();
        tasks.set(index, taskToUnmark);
        return taskToUnmark;
    }

    /**
     * Adds a task into the list of tasks.
     *
     * @param toAdd The task to be added.
     */
    public void add(Task toAdd) {
        this.tasks.add(toAdd);
    }

    /**
     * Deletes the task at a specific index of the list.
     *
     * @param index The index of a task on the list.
     * @return The task that is deleted.
     */
    public Task delete(int index) {
        Task removedTask = tasks.remove(index);
        return removedTask;
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

    /**
     * Sort tasks by description name.
     */
    public void sort() {
        Comparator<Task> nameComparator = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                return o1.description.compareTo(o2.description);
            }
        };
        tasks.sort(nameComparator);
    }

    /**
     * Returns the size of the list of tasks contained in this TaskList instance.
     *
     * @return The size of the list of tasks.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Converts the task list into its save format.
     *
     * @return The string representation of the save format of the task list.
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
