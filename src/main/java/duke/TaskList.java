package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a TaskList that holds tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private Storage dataStorage;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(String dataFilePath) {
        tasks = new ArrayList<>();
        dataStorage = new Storage(dataFilePath);
        load();
    }

    /**
     * Saves the list of tasks to the data file.
     */
    private void save() {
        try {
            dataStorage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Loads the list of tasks from the data file.
     */
    private void load() {
        try {
            ArrayList<Task> loadedTasks = dataStorage.loadTasks();
            tasks = loadedTasks;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the TaskList.
     *
     * @param task The task to be added.
     */
    public void add(Task task) {
        tasks.add(task);
        save();
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void delete(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        tasks.remove(index - 1);
        save();
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void mark(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        tasks.get(index - 1).markDone();
        save();
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void unmark(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        tasks.get(index - 1).markUndone();
        save();
    }

    /**
     * Retrieves a task at the specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public Task get(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        return tasks.get(index - 1);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Generates a string representation of the TaskList.
     *
     * @return A formatted string of tasks in the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i));
            if (i + 1 < tasks.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
