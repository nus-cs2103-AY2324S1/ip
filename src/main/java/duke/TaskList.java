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
            storage.saveToFile(list);
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
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
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
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        saveToFile();
    }

    /**
     * Deletes a task from the list and saves the list to storage.
     *
     * @param taskNum The index of the task to be deleted.
     */
    public void delete(int taskNum) {
        Task task = this.list.get(taskNum - 1);
        this.list.remove(taskNum - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " tasks in the list.");
        saveToFile();
    }

    /**
     * Searches for tasks that match a given input and returns a list of matches.
     *
     * @param input The search query to match against task descriptions.
     * @return An ArrayList containing tasks that match the input query.
     */
    public void findTasks(String input) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : this.list) {
            if (task.getTask().contains(input)) {
                matches.add(task);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println(i + 1 + ". " + matches.get(i));
        }
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
