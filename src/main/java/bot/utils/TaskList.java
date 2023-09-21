package bot.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import bot.exceptions.DuplicateTaskException;
import bot.exceptions.InvalidIndexException;
import bot.utils.tasks.Task;

/**
 * Task list abstraction. Functions like an arraylist but may not contain one. Does not accept duplicates.
 * Index starts at 1.
 */
public class TaskList {
    /**
     * Data structure to hold list.
     */
    private final List<Task> tasks = new ArrayList<>();

    /**
     * Default constructor. To be used when an empty list is needed.
     */
    public TaskList() {
    }

    /**
     * Constructor with a list of Task objects.
     *
     * @param list List of Task objects.
     */
    public TaskList(Collection<Task> list) {
        for (Task task : list) {
            try {
                add(task);
            } catch (DuplicateTaskException exception) {
                // We ignore duplicates because our task list should have unique items.
            }
        }
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task Task to add.
     * @throws DuplicateTaskException If the list already contains the task.
     */
    public void add(Task task) throws DuplicateTaskException {
        if (tasks.contains(task)) {
            throw new DuplicateTaskException();
        }
        tasks.add(task);
    }

    /**
     * Gets the task at that index of the list.
     *
     * @param index Index of task.
     * @return Bot.Task object.
     */
    public Task get(int index) {
        return tasks.get(index - 1);
    }

    /**
     * Removes the task at that index from the list.
     *
     * @param index Index of task.
     * @return Task object.
     * @throws InvalidIndexException If index is out of range.
     */
    public Task remove(int index) throws InvalidIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidIndexException();
        }
        return tasks.remove(index - 1);
    }

    /**
     * Gets length of the list.
     *
     * @return Length of list.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Gets an iterator of the tasks in the list.
     *
     * @return Iterator of Task objects.
     */
    public Iterator<Task> getIterator() {
        return tasks.iterator();
    }

    /**
     * Marks the task at the specified index
     *
     * @param index Index of task.
     * @throws InvalidIndexException If index is out of range.
     */
    public void mark(int index) throws InvalidIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(index - 1).mark();
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param index Index of task.
     * @throws InvalidIndexException If index is out of range.
     */
    public void unmark(int index) throws InvalidIndexException {
        if (index < 1 || index > tasks.size()) {
            throw new InvalidIndexException();
        }
        tasks.get(index - 1).unmark();
    }

    /**
     * Gets all tasks that contain the given string in their names.
     *
     * @param str Query string.
     * @return TaskList of tasks.
     */
    public TaskList findAll(String str) {
        Iterator<Task> iter = tasks.iterator();
        TaskList out = new TaskList();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.getName().toLowerCase().contains(str.toLowerCase())) {
                try {
                    out.add(task);
                } catch (DuplicateTaskException exception) {
                    // We ignore duplicate tasks because there's no better way to deal with them in this method.
                }
            }
        }
        return out;
    }
}
