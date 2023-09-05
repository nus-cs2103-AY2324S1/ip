package tasks;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * The `TaskList` class represents a collection of tasks in the Duke application.
 * It provides methods for adding, marking tasks as done/undone, deleting tasks,
 * and listing all tasks in the list.
 */
public class TaskList {
    /**
     * The list of tasks managed by this `TaskList`.
     */
    protected ArrayList<Task> tasks;

    /**
     * Constructs a `TaskList` with an initial array of tasks.
     *
     * @param tasks The initial array of tasks to populate the list.
     */
    public TaskList(Task[] tasks) {
        if (tasks == null) {
            this.tasks = new ArrayList<>(1);
        } else {
            this.tasks = new ArrayList<>(Arrays.asList(tasks));
        }
    }

    /**
     * Adds a task to the list and prints a confirmation message.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.printf("     %s\n", task);
        System.out.printf("Now you have %d tasks in the task list.\n", this.size());
    }

    /**
     * Marks a task as done and prints a confirmation message.
     *
     * @param text The text representing the task to be marked as done.
     */
    public void markTaskDone(String text) {
        Task task = this.tasks.get(Integer.parseInt(text) - 1);
        task.markDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.printf("     %s\n", task);
    }

    /**
     * Marks a task as undone and prints a confirmation message.
     *
     * @param text The text representing the task to be marked as undone.
     */
    public void markTaskUndone(String text) {
        Task task = this.tasks.get(Integer.parseInt(text) - 1);
        task.markUndone();
        System.out.println("Ok! I've marked this task as undone:");
        System.out.printf("     %s\n", task);
    }

    /**
     * Deletes a task from the list and prints a confirmation message.
     *
     * @param text The text representing the task to be deleted.
     */
    public void deleteTask(String text) {
        int taskIndex = Integer.parseInt(text) - 1;
        Task task = this.tasks.get(taskIndex);
        this.tasks.remove(taskIndex);
        System.out.println("Ok! I've deleted this task:");
        System.out.printf("     %s\n", task);
    }

    /**
     * Validates whether a task index is valid within the list.
     *
     * @param text The text representing the task index to validate.
     * @return `true` if the task index is valid, `false` otherwise.
     */
    public boolean validateTaskIndex(String text) {
        int index;
        try {
            Integer.parseInt(text);
        } catch (NumberFormatException e) {
            System.out.printf("Task index: '%s' is invalid, task index has to be an integer.\n", text);
            return false;
        }

        index = Integer.parseInt(text);

        if (index < 1 || index > this.tasks.size()) {
            System.out.printf("Task index: '%s' is invalid, task index has to be in list.\n", text);
            return false;
        }

        return true;
    }

    /**
     * Gets a task from the list by its index.
     *
     * @param taskIndex The index of the task to retrieve.
     * @return The task at the specified index, or `null` if the index is out of bounds.
     */
    public Task getTask(int taskIndex) {
        if (taskIndex < 0 || taskIndex >= tasks.size()) {
            return null;
        }
        return this.tasks.get(taskIndex);
    }

   /**
     * Gets the number of tasks in the list.
     *
     * @return The number of tasks in the list.
     */
    public int size() {
        return this.tasks.size();
    }

    /**
     * Retrieves all tasks in the list as an array.
     *
     * @return An array containing all the tasks in the list.
     */
    public Task[] getTasks() {
        Task[] taskArray = new Task[this.size()];
        return this.tasks.toArray(taskArray);
    }

    /**
     * Lists all tasks in the list and prints them along with task numbers.
     */
    public void listAllTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 1; i <= this.tasks.size(); ++i) {
            String taskNumber = String.format("%3d.", i);
            System.out.printf("%s %s\n", taskNumber, this.tasks.get(i - 1).toString());
        }
        System.out.printf("You have %d tasks in the task list.\n", this.size());
    }
}
