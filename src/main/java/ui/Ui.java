package ui;

import task.Task;

import java.util.Scanner;

/**
 * Represents the user interface of the Duke application.
 */
public class Ui {

    private final Scanner scanner;

    /**
     * Creates a new user interface instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the welcome message and logo.
     *
     * @return The welcome message and logo.
     */
    public String showWelcome() {
        String name = "misty";
        String logo = "|\\---/|\n" +
                "| o_o |\n" +
                " \\___/";
        return "Hello from " + name + "\n" + logo +
                "\nWhat can misty help you with?";
    }

    /**
     * Reads and returns the user's command input.
     *
     * @return The user's command input as a string.
     */
    public String readCommand() {
        return scanner.nextLine().trim();
    }

    /**
     * Provides the command guide with available commands.
     *
     * @return The command guide.
     */
    public String showCommandGuide() {
        return "Commands:\n" +
                "todo [task] - Adds a new ToDo task\n" +
                "deadline [task] /by [YYYY-MM-DD HH:mm] - Adds a new Deadline task\n" +
                "event [task] /from [YYYY-MM-DD HH:mm] /to [YYYY-MM-DD HH:mm] - Adds a new Event task\n" +
                "list - Shows all tasks\n" +
                "mark [task number] - Marks a task as completed\n" +
                "unmark [task number] - Marks a task as incomplete\n" +
                "delete [task number] - Deletes a task\n" +
                "find - Find a task by searching for a keyword\n" +
                "help - Shows command guide\n" +
                "bye - Exits Duke";
    }

    /**
     * Provides an error message related to loading tasks from a file.
     *
     * @param errorMessage The error message to display.
     * @return The formatted error message.
     */
    public static String loadingError(String errorMessage) {
        return "Error occurred when loading the tasks from the file: " + errorMessage;
    }

    /**
     * Provides an error message for an invalid task index.
     *
     * @param index The invalid task index.
     * @return The formatted error message.
     */
    public String invalidIndexError(int index) {
        return "Task " + index + " not found, please enter a valid task number.";
    }

    /**
     * Provides a general error message.
     *
     * @param errorMessage The error message to display.
     * @return The formatted error message.
     */
    public static String error(String errorMessage) {
        return errorMessage;
    }

    /**
     * Provides the header for the task list.
     *
     * @return The header for the task list.
     */
    public String showTaskListHeader() {
        return "Your tasks:";
    }

    /**
     * Provides a message indicating an empty task list.
     *
     * @return The message indicating an empty task list.
     */
    public String showEmptyTaskList() {
        return "You have no tasks :)";
    }

    /**
     * Provides a message indicating an empty search result.
     *
     * @return The message indicating an empty search result.
     */
    public String showNotFound() {
        return "None of the existing tasks match your search :(";
    }

    /**
     * Provides a task in the task list.
     *
     * @param index The task index.
     * @param task  The task to display.
     * @return The task in the task list.
     */
    public String showTask(int index, Task task) {
        return (index + 1) + ") " + task;
    }

    /**
     * Provides a message indicating that a task has been added.
     *
     * @param task The added task.
     * @return The message indicating that a task has been added.
     */
    public String showAddedTask(Task task) {
        return "Added: " + task;
    }

    /**
     * Provides a message indicating that a task has been marked as completed.
     *
     * @param index The task index.
     * @param task  The marked task.
     * @return The message indicating that a task has been marked as completed.
     */
    public String showMarkedTask(int index, Task task) {
        return "Ok, you have completed task " + (index) + ": " + task;
    }

    /**
     * Provides a message indicating that a task has been marked as incomplete.
     *
     * @param index The task index.
     * @param task  The unmarked task.
     * @return The message indicating that a task has been marked as incomplete.
     */
    public String showUnmarkedTask(int index, Task task) {
        return "Ok, you haven't completed task " + (index) + ": " + task;
    }

    /**
     * Provides a message indicating that a task has been deleted.
     *
     * @param index       The task index.
     * @param deletedTask The deleted task.
     * @return The message indicating that a task has been deleted.
     */
    public String showDeletedTask(int index, Task deletedTask) {
        return "Task " + (index) + " has been removed\n" + deletedTask + " has been deleted.";
    }

    /**
     * Provides an error message related to saving tasks to a file.
     *
     * @param errorMessage The error message to display.
     * @return The formatted error message.
     */
    public static String saveError(String errorMessage) {
        return "An error occurred while saving the tasks to a file: " + errorMessage;
    }

    /**
     * Provides the goodbye message when exiting the application.
     *
     * @return The goodbye message.
     */
    public String showGoodbye() {
        return "Ok, thanks. Bye!";
    }

    /**
     * Provides a horizontal line to separate messages.
     *
     * @return The horizontal line.
     */
    public String showLine() {
        return "------------------------------------------------";
    }
}
