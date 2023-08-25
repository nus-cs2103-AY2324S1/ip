import java.util.ArrayList;
import java.io.IOException;

/**
 * Represents a TaskList that holds tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Storage dataStorage;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList(String dataFilePath) {
        list = new ArrayList<>();
        dataStorage = new Storage(dataFilePath);
        load();
    }

    /**
     * Save the list of tasks to the data file.
     */
    private void save() {
        try {
            dataStorage.saveTasks(list);
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    /**
     * Load the list of tasks from the data file.
     */
    private void load() {
        try {
            ArrayList<Task> tasks = dataStorage.loadTasks();
            list = tasks;
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
        list.add(task);
        save();
    }

    /**
     * Deletes a task at the specified index.
     *
     * @param index The index of the task to be deleted.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void delete(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.remove(index - 1);
        save();
    }

    /**
     * Marks a task at the specified index as done.
     *
     * @param index The index of the task to be marked as done.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void mark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.get(index - 1).markDone();
        save();
    }

    /**
     * Marks a task at the specified index as undone.
     *
     * @param index The index of the task to be marked as undone.
     * @throws IndexOutOfBoundsException If the index is out of range.
     */
    public void unmark(int index) {
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        list.get(index - 1).markUndone();
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
        if (index < 1 || index > list.size()) {
            throw new IndexOutOfBoundsException("Task index is out of range.");
        }
        return list.get(index - 1);
    }

    /**
     * Returns the number of tasks in the TaskList.
     *
     * @return The number of tasks.
     */
    public int size() {
        return list.size();
    }

    /**
     * Generates a string representation of the TaskList.
     *
     * @return A formatted string of tasks in the TaskList.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            result.append((i + 1) + ". " + list.get(i));
            if (i + 1 < list.size()) {
                result.append("\n");
            }
        }
        return result.toString();
    }
}
