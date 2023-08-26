import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Task list abstraction. Functions like an arraylist but may not contain one.
 * Index starts at 1.
 */
public class TaskList {
    /**
     * Data structure to hold list.
     */
    private final List<Task> list = new ArrayList<>();

    /**
     * Default constructor. To be used when an empty list is needed.
     */
    public TaskList() {}

    /**
     * Constructor with a list of Task objects.
     *
     * @param list List of Task objects.
     */
    public TaskList(Collection<Task> list) {
        this.list.addAll(list);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task Task to add.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Gets the task at that index of the list.
     *
     * @param index Index of task.
     * @return Task object.
     */
    public Task get(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Removes the task at that index from the list.
     *
     * @param index Index of task.
     * @return Task object.
     */
    public Task remove(int index) {
        return this.list.remove(index - 1);
    }

    /**
     * Gets length of the list.
     *
     * @return Length of list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Gets an iterator of the tasks in the list.
     *
     * @return Iterator of Task objects.
     */
    public Iterator<Task> iterator() {
        return this.list.iterator();
    }
}
