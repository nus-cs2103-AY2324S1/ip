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
     * Displays a welcome message when Duke starts.
     */
    public static void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("\t____________________________________________________________");
        System.out.println("\t Hello! I'm Duke.");
        System.out.println("\t What can I do for you?");
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Displays an exit message when Duke is exited.
     */
    public static void showExit() {
        System.out.println("\t Bye. Hope to see you again soon!");
    }

    /**
     * Displays an error message when loading tasks from storage fails.
     */
    public static void showLoadingError() {
        System.out.println("No saved tasks found.");
    }

    /**
     * Displays an error message with the provided error message.
     *
     * @param errorMessage The error message to display.
     */

    public static void showError(String errorMessage) {
        System.out.println(errorMessage);
    }

    /**
     * Displays a horizontal line separator.
     */
    public static void showLine() {
        System.out.println("\t____________________________________________________________");
    }

    /**
     * Displays a message when a task is added.
     *
     * @param task The task that was added.
     * @param size The number of tasks in the list after adding.
     */
    public static void showAdded(Task task, int size) {
        System.out.println("\t Got it. I've added this task: ");
        System.out.println("\t\t" + task);
        System.out.println("\t Now you have " + size + " task(s) in the list.");
    }

    /**
     * Displays a message when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The number of tasks in the list after deletion.
     */
    public static void showDeleted(Task task, int size) {
        System.out.println("\t Noted. I've removed this task:");
        System.out.println("\t   " + task);
        System.out.println("\t Now you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message when a task is marked as done.
     *
     * @param task The task that was marked as done.
     */
    public static void showMarked(Task task) {
        System.out.println("\t Nice! I've marked this task as done:");
        System.out.println("\t   " + task);
    }

    /**
     * Displays a message when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     */
    public static void showUnmarked(Task task) {
        System.out.println("\t OK, I've marked this task as not done yet:");
        System.out.println("\t   " + task);
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The list of tasks to display.
     */
    public static void showList(TaskList tasks) {
        tasks.listTasks();
    }

    /**
     * Displays matching tasks from a TaskList.
     *
     * @param matchingTasks The TaskList containing matching tasks.
     */
    public void showMatchingTasks(TaskList matchingTasks) {
        System.out.println("\t Here are the matching tasks in your list:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.println("\t " + (i + 1) + "." + matchingTasks.get(i));
        }
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
