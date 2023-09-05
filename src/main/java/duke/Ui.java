package duke;

import java.util.Scanner;

import duke.task.Task;
/**
 * Represents a User interface
 */
public class Ui {
    private Scanner scanner;

    /**
     * Constructs a `Ui` object and initializes the scanner for user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Displays a welcome message when the program starts.
     */
    public void showWelcomeMessage() {
        System.out.println("Hi! This is your AI assistant LoyBoy!");
        System.out.println("What can I do for you today?");
    }

    /**
     * Reads and returns user input as a string.
     *
     * @return The user's input as a string.
     */
    public String getUserInput() {
        System.out.print("\nEnter a command: ");
        return scanner.nextLine();
    }

    /**
     * Displays a message when a task is added to the task list.
     *
     * @param task       The task that was added.
     * @param totalTasks The total number of tasks in the list.
     */
    public void showTaskAddedMessage(Task task, int totalTasks) {
        System.out.println("Got it! I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " task(s) in the list.");
    }

    /**
     * Displays a message when a task is removed from the task list.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks in the list after removal.
     */
    public void showTaskRemoveMessage(Task task, int totalTasks) {
        System.out.println("Yes Sir. I've removed the following task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + totalTasks + " task(s) in the list.");
    }

    public void showNoMatchingTasksMessage() {
        System.out.println("No matching tasks found in your list :(");
    }
    public void showFindTaskMessage() {
        System.out.println("Here are the matching tasks in your list:)");
    }
    public void showGoodbyeMessage() {
        System.out.println("I wish you a pleasant day ahead, goodbye!");
    }
    public void showLoadingError() {
        System.err.println("Error! Cannot load tasks from data file.");
    }
    public void closeScanner() {
        scanner.close();
    }
}
