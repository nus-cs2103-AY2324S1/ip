package duke;

import java.util.ArrayList;
import java.util.List;

import duke.task.Task;

/**
 * The DukeList class represents a list of tasks.
 */
public class DukeList {
    private final List<Task> dukeList;

    /**
     * Constructs a new DukeList object with an empty list of tasks.
     */
    public DukeList() {
        dukeList = new ArrayList<>(100);
    }

    /**
     * Constructs a new DukeList object with a provided list of tasks.
     *
     * @param list The initial list of tasks.
     */
    public DukeList(ArrayList<Task> list) {
        dukeList = list;
    }

    /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int getSize() {
        return dukeList.size();
    }

    /**
     * Retrieves a task from the list based on its index.
     *
     * @param key The index of the task to retrieve.
     * @return The task at the specified index.
     */
    public Task get(int key) {
        return dukeList.get(key);
    }

    /**
     * Adds a task to the list.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        dukeList.add(task);
    }

    /**
     * Deletes a task from the list based on its index.
     *
     * @param key The index of the task to be deleted.
     */
    public void delete(int key) {
        dukeList.remove(key);
    }

    /**
     * Returns a string representation of the DukeList.
     *
     * @return A formatted string containing the tasks in the list.
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 1; i <= dukeList.size(); i++) {
            Task task = dukeList.get(i - 1);
            str.append(i).append(". ").append(task.toString()).append("\n");
        }
        return str.toString();
    }
}
