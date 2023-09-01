package duke;

import duke.tasks.Task;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList implements Serializable {
    private static final long serialVersionUID = 5L;

    private final List<Task> tasks;

    private TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList() {
        this(new ArrayList<>());
    }

    /**
     * Returns whether the task list is empty.
     *
     * @return True if the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Returns the size of the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns the task at the specified position.
     *
     * @param i The index of the task to return.
     * @return The task at the specified position.
     */
    public Task get(int i) {
        return tasks.get(i);
    }

    /**
     * Adds a task to the end of the task list.
     *
     * @param t The task to be added to the list.
     */
    public void add(Task t) {
        tasks.add(t);
    }

    public TaskList filter(Predicate<? super Task> cond) {
        return new TaskList(tasks.stream().filter(cond).collect(Collectors.toList()));
    }

    /**
     * Removes the task at the specified position.
     *
     * @param i The index of the task to be removed.
     */
    public void remove(int i) {
        tasks.remove(i);
    }
}
