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
     */
    public void showWelcome() {
        String name = "misty";
        String logo = "|\\---/|\n" +
                "| o_o |\n" +
                " \\___/";
        System.out.println(">  Hello from " + name + "\n" + logo);
        System.out.println(">  What can misty help you with?");
        showLine();
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
     * Displays the command guide with available commands.
     */
    public void showCommandGuide() {
        System.out.println(">  Commands:");
        System.out.println("todo [task] - Adds a new ToDo task");
        System.out.println("deadline [task] /by [YYYY-MM-DD HH:mm] - Adds a new task.Deadline task");
        System.out.println("event [task] /from [YYYY-MM-DD HH:mm] /to [YYYY-MM-DD HH:mm] - Adds a new task.Event task");
        System.out.println("list - Shows all tasks");
        System.out.println("mark [task number] - Marks a task as completed");
        System.out.println("unmark [task number] - Marks a task as incomplete");
        System.out.println("delete [task number] - Deletes a task");
        System.out.println("help - Shows command guide");
        System.out.println("bye - Exits Duke");
    }

    /**
     * Displays an error message related to loading tasks from a file.
     *
     * @param errorMessage The error message to display.
     * @return The formatted error message.
     */
    public static String loadingError(String errorMessage) {
        return ">  Error occurred when loading the tasks from the file: " + errorMessage;
    }

    /**
     * Displays an error message for an invalid task index.
     *
     * @param index The invalid task index.
     * @return The formatted error message.
     */
    public String invalidIndexError(int index) {
        return ">  task.Task " + index + " not found, please enter a valid task number.";
    }

    /**
     * Displays a general error message.
     *
     * @param errorMessage The error message to display.
     * @return The formatted error message.
     */
    public static String error(String errorMessage) {
        return errorMessage;
    }

    /**
     * Displays the header for the task list.
     */
    public void showTaskListHeader() {
        System.out.println(">  Your tasks: ");
    }

    /**
     * Displays a message indicating an empty task list.
     */
    public void showEmptyTaskList() {
        System.out.println(">  You have no tasks :)");
    }

    /**
     * Displays a task in the task list.
     *
     * @param index The task index.
     * @param task  The task to display.
     */
    public void showTask(int index, Task task) {
        System.out.println((index + 1) + ") " + task);
    }

    /**
     * Displays a message indicating that a task has been added.
     *
     * @param task The added task.
     */
    public void showAddedTask(Task task) {
        System.out.println(">  Added: " + task);
    }

    /**
     * Displays a message indicating that a task has been marked as completed.
     *
     * @param index The task index.
     * @param task  The marked task.
     */
    public void showMarkedTask(int index, Task task) {
        System.out.println(">  ok, you have completed task.Task " + (index + 1));
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been marked as incomplete.
     *
     * @param index The task index.
     * @param task  The unmarked task.
     */
    public void showUnmarkedTask(int index, Task task) {
        System.out.println(">  ok, you haven't completed task.Task " + (index + 1));
        System.out.println(task);
    }

    /**
     * Displays a message indicating that a task has been deleted.
     *
     * @param index       The task index.
     * @param deletedTask The deleted task.
     */
    public void showDeletedTask(int index, Task deletedTask) {
        System.out.println(">  task.Task " + (index) + " has been removed");
        System.out.println(">  " + deletedTask + " has been deleted.");
    }

    /**
     * Displays an error message related to saving tasks to a file.
     *
     * @param errorMessage The error message to display.
     * @return The formatted error message.
     */
    public static String saveError(String errorMessage) {
        return ">  An error occurred while saving the tasks to a file: " + errorMessage;
    }

    /**
     * Displays the goodbye message when exiting the application.
     */
    public void showGoodbye() {
        System.out.println(">  ok thanks bye");
    }

    /**
     * Displays a horizontal line to separate messages.
     */
    public void showLine() {
        System.out.println("------------------------------------------------");
    }
}
