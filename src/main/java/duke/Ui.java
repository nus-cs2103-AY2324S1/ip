package duke;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents the user interface for Duke, handling input and output.
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a Ui object and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message when Duke starts and returns it as a String.
     *
     * @return The welcome message.
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from\n" + logo + "\n"
                + "____________________________________________________________\n"
                + " Hello! I'm Duke.\n"
                + " What can I do for you?\n"
                + "____________________________________________________________";
        return welcomeMessage;
    }

    /**
     * Displays an exit message when Duke is exited and returns it as a String.
     *
     * @return The exit message.
     */
    public String showExit() {
        return "Bye. Hope to see you again soon! Please close this window.";
    }

    /**
     * Displays an error message when loading tasks from storage fails and returns it as a String.
     *
     * @return The loading error message.
     */
    public String showLoadingError() {
        return "No saved tasks found.";
    }

    /**
     * Displays an error message with the provided error message and returns it as a String.
     *
     * @param errorMessage The error message to display.
     * @return The error message.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns a horizontal line separator as a String.
     *
     * @return The horizontal line separator.
     */
    public String showLine() {
        return "____________________________________________________________";
    }

    /**
     * Returns a message when a task is added as a String.
     *
     * @param task The task that was added.
     * @param size The number of tasks in the list after adding.
     * @return The message for adding a task.
     */
    public String showAdded(Task task, int size) {
        return "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + size + " task(s) in the list.";
    }

    /**
     * Returns a message when a task is deleted as a String.
     *
     * @param task The task that was deleted.
     * @param size The number of tasks in the list after deletion.
     * @return The message for deleting a task.
     */
    public String showDeleted(Task task, int size) {
        return "Noted. I've removed this task:\n"
                + "   " + task + "\n"
                + " Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message when a task is marked as done as a String.
     *
     * @param task The task that was marked as done.
     * @return The message for marking a task as done.
     */
    public String showMarked(Task task) {
        return "Nice! I've marked this task as done:\n"
                + "   " + task;
    }

    /**
     * Returns a message when a task is marked as not done as a String.
     *
     * @param task The task that was marked as not done.
     * @return The message for marking a task as not done.
     */
    public String showUnmarked(Task task) {
        return "OK, I've marked this task as not done yet:\n"
                + "   " + task;
    }

    /**
     * Returns a list of tasks as a String.
     *
     * @param tasks The list of tasks to display.
     * @return The list of tasks as a String.
     */
    public String showList(TaskList tasks) {
        return tasks.listTasks();
    }

    /**
     * Returns matching tasks from a TaskList as a String.
     *
     * @param matchingTasks The TaskList containing matching tasks.
     * @return The matching tasks as a String.
     */
    public String showMatchingTasks(TaskList matchingTasks) {
        StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
        }
        return result.toString();
    }

    /**
     * Reads a command from the user's input.
     *
     * @return The user's input command.
     */
    public String readCommand() {
        return scanner.nextLine();
    }
}
