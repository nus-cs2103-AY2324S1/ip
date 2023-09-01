package duke;

import java.util.Scanner;

/**
 * Represents the User Interface (UI) of the duke application.
 * This class handles all interactions with the user.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Initializes a new Ui instance.
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Reads and returns a line of input from the user.
     *
     * @return User's input string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays the welcome message when the program starts.
     */
    public void showWelcome() {
        printLine("____________________________________________________________");
        printLine("Hello! I'm duke.Duke");
        printLine("What can I do for you?");
        printLine("____________________________________________________________");
    }

    /**
     * Displays the goodbye message when the program ends.
     */
    public void showGoodbye() {
        printLine("____________________________________________________________");
        printLine("Bye. Hope to see you again soon!");
        printLine("____________________________________________________________");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        printLine("____________________________________________________________");
        printLine("☹ OOPS!!! " + message);
        printLine("____________________________________________________________");
    }

    /**
     * Displays a loading error message.
     */
    public void showLoadingError() {
        showError("There was an error loading saved tasks.");
    }

    /**
     * Displays a message surrounded by lines.
     *
     * @param message The message to be displayed.
     */
    public void printMessage(String message) {
        printLine("____________________________________________________________");
        printLine(message);
        printLine("____________________________________________________________");
    }

    /**
     * Displays a line of text.
     *
     * @param line The text to be displayed.
     */
    private void printLine(String line) {
        System.out.println(line);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void showTasksList(TaskList tasks) {
        printLine("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            printLine((i + 1) + "." + task);
        }
    }
    /**
     * Displays a message indicating a task has been added.
     *
     * @param task        The task that was added.
     * @param totalTasks  The total number of tasks after adding.
     */
    public void showAddedTask(Task task, int totalTasks) {
        printMessage("Got it. I've added this task:\n  " + task + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task        The task that was deleted.
     * @param totalTasks  The total number of tasks after deletion.
     */
    public void showDeletedTask(Task task, int totalTasks) {
        printMessage("Noted. I've removed this task:\n  " + task + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays an error message related to task saving.
     *
     * @param errorMessage The specific error message regarding saving.
     */
    public void showSavingError(String errorMessage) {
        printLine("Error saving tasks: " + errorMessage);
    }

    /**
     * Displays a message indicating an invalid task number.
     */
    public void showInvalidTaskNumber() {
        printMessage("Please provide a valid task number.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public void showTaskMarked(Task task) {
        printLine("____________________________________________________________");
        printLine("Nice! I've marked this task as done:");
        printLine("  [" + task.getStatusIcon() + "] " + task.description);
        printLine("____________________________________________________________");
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public void showTaskUnmarked(Task task) {
        printLine("____________________________________________________________");
        printLine("OK, I've marked this task as not done yet:");
        printLine("  [" + task.getStatusIcon() + "] " + task.description);
        printLine("____________________________________________________________");
    }
}
