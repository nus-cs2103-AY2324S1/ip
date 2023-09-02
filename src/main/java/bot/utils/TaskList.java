package bot.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import bot.exceptions.InvalidIndexException;

/**
 * Bot.Task list abstraction. Functions like an arraylist but may not contain one.
 * Index starts at 1.
 */
public class TaskList {
    /**
     * Data structure to hold list.
     */
    private List<Task> list = new ArrayList<>();

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
        this.list.addAll(list);
    }

    /**
     * Adds a task to the end of the list.
     *
     * @param task Bot.Task to add.
     */
    public void add(Task task) {
        this.list.add(task);
    }

    /**
     * Gets the task at that index of the list.
     *
     * @param index Index of task.
     * @return Bot.Task object.
     */
    public Task get(int index) {
        return this.list.get(index - 1);
    }

    /**
     * Removes the task at that index from the list.
     *
     * @param index Index of task.
     * @return Task object.
     * @throws InvalidIndexException If index is out of range.
     */
    public Task remove(int index) throws InvalidIndexException {
        if (index < 1 || index > list.size()) {
            throw new InvalidIndexException();
        }
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

    /**
     * Marks the task at the specified index
     *
     * @param index Index of task.
     * @throws InvalidIndexException If index is out of range.
     */
    public void mark(int index) throws InvalidIndexException {
        if (index < 1 || index > list.size()) {
            throw new InvalidIndexException();
        }
        this.list.get(index - 1).mark();
    }

    /**
     * Unmarks the task at the specified index.
     *
     * @param index Index of task.
     * @throws InvalidIndexException If index is out of range.
     */
    public void unmark(int index) throws InvalidIndexException {
        if (index < 1 || index > list.size()) {
            throw new InvalidIndexException();
        }
        this.list.get(index - 1).unmark();
    }

    /**
     * Gets all tasks that contain the given string in their names.
     *
     * @param str Query string.
     * @return TaskList of tasks.
     */
    public TaskList findAll(String str) {
        Iterator<Task> iter = list.iterator();
        TaskList out = new TaskList();
        while (iter.hasNext()) {
            Task task = iter.next();
            if (task.getName().contains(str)) {
                out.add(task);
            }
        }
        return out;
    }
}
