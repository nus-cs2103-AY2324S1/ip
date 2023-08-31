package duke;

import java.io.IOException;

import java.util.ArrayList;

/**
 * The TaskList class represents a list of tasks in the Duke application.
 * It manages tasks, their operations, and interactions with storage.
 */
public class TaskList {
    private ArrayList<Task> list;
    private final Storage storage;

    /**
     * Constructs a TaskList object with the specified file path for task storage.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public TaskList(String filePath) {
        this.list = new ArrayList<>();
        this.storage = new Storage(filePath);
        fetchList();
    }

    /**
     * Fetches the list of tasks from storage.
     */
    public void fetchList() {
        try {
            this.list = this.storage.load();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error when fetching todo list: " + e.getMessage());
        }
    }

    /**
     * Saves the current list of tasks to storage.
     */
    public void saveToFile() {
        try {
            storage.saveToFile(this.list);
        } catch (IOException e) {
            System.out.println("Error while saving to-do list: " + e.getMessage());
        }
    }

    /**
     * Adds a task to the list and saves the list to storage.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.list.add(task);
        Ui.addTask(task);
        Ui.countTasks(this.list);
        saveToFile();
    }

    /**
     * Marks a task as done and saves the list to storage.
     *
     * @param taskNum The index of the task to be marked as done.
     */
    public void markAsDone(int taskNum) {
        Task task = this.list.get(taskNum - 1);
        task.markAsDone();
        Ui.markAsDone(task);
        saveToFile();
    }

    /**
     * Deletes a task from the list and saves the list to storage.
     *
     * @param taskNum The index of the task to be deleted.
     */
    public void deleteTask(int taskNum) {
        Task task = this.list.get(taskNum - 1);
        this.list.remove(taskNum - 1);
        Ui.deleteTask(task);
        Ui.countTasks(this.list);
        saveToFile();
    }

    /**
     * Searches for tasks that match a given input and returns a list of matches.
     *
     * @param input The search query to match against task descriptions.
     */
    public void findTasks(String input) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getTask().contains(input)) {
                matches.add(task);
            }
        }
        Ui.findTasks(matches);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.list.size();
    }

    /**
     * Retrieves a task from the list at a specified index.
     *
     * @param index The index of the task to be retrieved.
     * @return The task at the specified index.
     */
    public Task get(int index) {
        return this.list.get(index);
    }
}
