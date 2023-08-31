package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;


/**
 * Represents a list of tasks.
 * Inherits from the ArrayList class and specializes for tasks.
 */
public class TaskList extends ArrayList<Task> {
    ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object.
     * Initializes the internal ArrayList to store tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return Always returns `false`.
     */
    @Override
    public boolean add(Task task) {
        tasks.add(task);
        return false;
    }

    /**
     * Retrieves a task from the task list by index.
     *
     * @param i The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Removes a task from the task list by index.
     *
     * @param i The index of the task to remove.
     * @return Always returns `null`.
     */
    public Task remove(int i) {
        tasks.remove(i);
        return null;
    }

    @Override
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    @Override
    public int size() {
        return tasks.size();
    }
    @Override
    public boolean contains(Object obj) {
        return tasks.contains(obj);
    }
    @Override
    public void forEach(Consumer<? super Task> action) {
        tasks.forEach(action);
    }
}
