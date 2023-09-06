package duke;

import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

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
     */
    public String showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String welcomeMessage = "Hello from\n" + logo + "\n"
                + "\t____________________________________________________________\n"
                + "\t Hello! I'm Duke.\n"
                + "\t What can I do for you?\n"
                + "\t____________________________________________________________";
        return welcomeMessage;
    }

    /**
     * Displays an exit message when Duke is exited and returns it as a String.
     */
    public String showExit() {
        return "\t Bye. Hope to see you again soon!";
    }

    /**
     * Displays an error message when loading tasks from storage fails and returns it as a String.
     */
    public String showLoadingError() {
        return "No saved tasks found.";
    }

    /**
     * Displays an error message with the provided error message and returns it as a String.
     *
     * @param errorMessage The error message to display.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Returns a horizontal line separator as a String.
     */
    public String showLine() {
        return "\t____________________________________________________________";
    }

    /**
     * Returns a message when a task is added as a String.
     *
     * @param task The task that was added.
     * @param size The number of tasks in the list after adding.
     */
    public String showAdded(Task task, int size) {
        return "\t Got it. I've added this task:\n"
                + "\t\t" + task + "\n"
                + "\t Now you have " + size + " task(s) in the list.";
    }

    /**
     * Returns a message when a task is deleted as a String.
     *
     * @param task The task that was deleted.
     * @param size The number of tasks in the list after deletion.
     */
    public String showDeleted(Task task, int size) {
        return "\t Noted. I've removed this task:\n"
                + "\t   " + task + "\n"
                + "\t Now you have " + size + " tasks in the list.";
    }

    /**
     * Returns a message when a task is marked as done as a String.
     *
     * @param task The task that was marked as done.
     */
    public String showMarked(Task task) {
        return "\t Nice! I've marked this task as done:\n"
                + "\t   " + task;
    }

    /**
     * Returns a message when a task is marked as not done as a String.
     *
     * @param task The task that was marked as not done.
     */
    public String showUnmarked(Task task) {
        return "\t OK, I've marked this task as not done yet:\n"
                + "\t   " + task;
    }

    /**
     * Returns a list of tasks as a String.
     *
     * @param tasks The list of tasks to display.
     */
    public String showList(TaskList tasks) {
        return tasks.listTasks();
    }

    /**
     * Returns matching tasks from a TaskList as a String.
     *
     * @param matchingTasks The TaskList containing matching tasks.
     */
    public String showMatchingTasks(TaskList matchingTasks) {
        StringBuilder result = new StringBuilder("\t Here are the matching tasks in your list:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append("\t ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
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
