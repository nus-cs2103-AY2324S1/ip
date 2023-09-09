package duke;

import java.util.ArrayList;
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
    public static String showWelcome() {
        return "____________________________________________________________\n"
               + "Hello! I'm Duke\n"
               + "What can I do for you?\n"
               + "____________________________________________________________\n";
    }


    /**
     * Displays the goodbye message when the program ends.
     */
    public String showGoodbye() {
        return "____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________\n";
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to be displayed.
     */
    public String showError(String message) {
        return "____________________________________________________________\n"
                + "☹ OOPS!!! " + message + "\n"
                + "____________________________________________________________\n";
    }

    /**
     * Displays a loading error message.
     */
    public String showLoadingError() {
        return showError("There was an error loading saved tasks.");
    }

    /**
     * Displays a message surrounded by lines.
     *
     * @param message The message to be displayed.
     */
    public String printMessage(String message) {
        return "____________________________________________________________\n"
                + message + "\n"
                + "____________________________________________________________\n";
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
    public String showTasksList(TaskList tasks) {
        StringBuilder builder = new StringBuilder();
        builder.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            builder.append((i + 1) + "." + task).append("\n");
        }
        return builder.toString();
    }
    /**
     * Displays a message indicating a task has been added.
     *
     * @param task        The task that was added.
     * @param totalTasks  The total number of tasks after adding.
     */
    public String showAddedTask(Task task, int totalTasks) {
        return printMessage("Got it. I've added this task:\n  " + task
                + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays a message indicating a task has been deleted.
     *
     * @param task        The task that was deleted.
     * @param totalTasks  The total number of tasks after deletion.
     */
    public String showDeletedTask(Task task, int totalTasks) {
        return printMessage("Noted. I've removed this task:\n  " + task
                + "\nNow you have " + totalTasks + " tasks in the list.");
    }

    /**
     * Displays an error message related to task saving.
     *
     * @param errorMessage The specific error message regarding saving.
     */
    public String showSavingError(String errorMessage) {
        return "Error saving tasks: " + errorMessage + "\n";
    }

    /**
     * Displays a message indicating an invalid task number.
     */
    public String showInvalidTaskNumber() {
        return printMessage("Please provide a valid task number.");
    }

    /**
     * Displays a message indicating a task has been marked as done.
     *
     * @param task The task that was marked.
     */
    public String showTaskMarked(Task task) {
        return "____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + "  [" + task.getStatusIcon() + "] " + task.description + "\n"
                + "____________________________________________________________\n";
    }

    /**
     * Displays a message indicating a task has been marked as not done.
     *
     * @param task The task that was unmarked.
     */
    public String showTaskUnmarked(Task task) {
        return "____________________________________________________________\n"
                + "OK, I've marked this task as not done yet:\n"
                + "  [" + task.getStatusIcon() + "] " + task.description + "\n"
                + "____________________________________________________________\n";
    }

    /**
     * Displays the tasks that match the provided keyword.
     *
     * @param matchingTasks A list of tasks that match the keyword.
     */
    public String showMatchingTasks(ArrayList<Task> matchingTasks) {
        StringBuilder builder = new StringBuilder();

        if (matchingTasks.isEmpty()) {
            return printMessage("No tasks match your search.");
        }

        builder.append("____________________________________________________________\n");
        builder.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            Task task = matchingTasks.get(i);
            builder.append((i + 1) + "." + task).append("\n");
        }
        builder.append("____________________________________________________________\n");

        return builder.toString();
    }
}
