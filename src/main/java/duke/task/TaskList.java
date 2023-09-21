package duke.task;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Represents a list of tasks and provides methods to manipulate tasks within
 * the list.
 */
public class TaskList implements Iterable<Task> {
    private final ArrayList<Task> items;

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        items = new ArrayList<>();
    }

    /**
     * Constructs a task list with an initial set of tasks.
     *
     * @param items The list of tasks to initialize the task list with.
     */
    public TaskList(ArrayList<Task> items) {
        this.items = items;
    }

    /**
     * Returns the TaskList as an ArrayList.
     * @return an ArrayList of tasks.
     */
    public ArrayList<Task> getArrayList() {
        return items;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getCount() {
        return items.size();
    }

    /**
     * Finds and returns the task(s) found based on the search keyword.
     *
     * @param keyword The text used to search for tasks, based on their names.
     * @return The task(s) found with matching name(s).
     */
    public ArrayList<Task> searchFor(String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task item : items) {
            if (item.getName().contains(keyword)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Marks a task as done based on its position in the list.
     *
     * @param number The position of the task in the list (1-based index).
     * @return The task that was marked as done.
     */
    public Task mark(int number) {
        items.get(number - 1).markDone();
        return items.get(number - 1);
    }

    /**
     * Marks a task as not done based on its position in the list.
     *
     * @param number The position of the task in the list (1-based index).
     * @return The task that was marked as not done.
     */
    public Task unmark(int number) {
        items.get(number - 1).markUndone();
        return items.get(number - 1);
    }

    /**
     * Deletes a task based on its position in the list.
     *
     * @param number The position of the task in the list (1-based index).
     * @return The task that was removed from the list.
     */
    public Task delete(int number) {
        return items.remove(number - 1);
    }

    /**
     * Adds a new to-do task to the list.
     *
     * @param name The name of the to-do task.
     * @return The task that was added to the list.
     */
    public Task addToDo(String name) {
        items.add(new ToDo(name, false));
        return items.get(items.size() - 1);
    }

    /**
     * Adds a new deadline task to the list.
     *
     * @param name The name of the deadline task.
     * @param by   The deadline for the task.
     * @return The task that was added to the list.
     */
    public Task addDeadline(String name, LocalDateTime by) {
        items.add(new Deadline(name, by, false));
        return items.get(items.size() - 1);
    }

    /**
     * Adds a new event task to the list.
     *
     * @param name The name of the event task.
     * @param from The start time of the event.
     * @param to   The end time of the event.
     * @return The task that was added to the list.
     */
    public Task addEvent(String name, LocalDateTime from, LocalDateTime to) {
        items.add(new Event(name, from, to, false));
        return items.get(items.size() - 1);
    }


    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Task> iterator() {
        return items.iterator();
    }


}
