package chatbot.alain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> list;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList with an existing list of tasks.
     *
     * @param existingList An ArrayList containing existing tasks.
     */
    public TaskList(ArrayList<Task> existingList) {
        this.list = existingList;
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Removes a task from the list at the specified index.
     *
     * @param index The index of the task to be removed.
     * @return The removed task.
     * @throws AlainException If the index is out of bounds.
     */
    public Task removeTask(int index) throws AlainException {
        if (index >= 0 && index < list.size()) {
            return list.remove(index);
        } else {
            throw new AlainException(" OOPS!!! Seems like alain.Task with such index does not exist");
        }
    }

    /**
     * Retrieves a task from the list at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index or null if the index is out of bounds.
     */
    public Task getTask(int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }

    /**
     * Returns the ArrayList of tasks.
     *
     * @return The ArrayList containing tasks.
     */
    public ArrayList<Task> getTasks() {
        return list;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return list.size();
    }

    /**
     * Checks if the task is present in the list.
     *
     * @param task The task to be checked.
     * @return true if the task is found in the list, false otherwise.
     */
    public boolean contains(Task task) {
        return list.contains(task);
    }

    /**
     * Sorts the tasks in the list based on their date values.
     *
     * The sorting is achieved by converting the date of each task to its respective value
     * and then sorting the tasks in ascending order of these values. After sorting,
     * the list is updated with the sorted tasks.
     *
     * @see Task#getDate()
     */
    public void sort() {
        list = (ArrayList<Task>) list.stream()
                .sorted(Comparator.comparing(Task::getDate))
                .collect(Collectors.toList());
    }
}
