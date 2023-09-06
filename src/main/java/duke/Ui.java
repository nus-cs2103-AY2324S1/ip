package duke;

import java.util.Scanner;
import duke.task.Task;

/**
 * Represents a User interface for interacting with the Duke application.
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
     *
     * @return A welcome message as a string.
     */
    public String showWelcomeMessage() {
        return "Hi! This is your AI assistant LoyBoy!\n Remember to type 'save' to save" +
                " your list! What can I do for you today?";
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
     * @return A message indicating the task addition.
     */
    public String showTaskAddedMessage(Task task, int totalTasks) {
        String response = "Got it! I've added this task: \n" + task +
                "\n" + "Now you have " + totalTasks + " task(s) in the list.";
        return response;
    }

    /**
     * Displays a message when a task is removed from the task list.
     *
     * @param task       The task that was removed.
     * @param totalTasks The total number of tasks in the list after removal.
     * @return A message indicating the task removal.
     */
    public String showTaskRemoveMessage(Task task, int totalTasks) {
        String response = "Yes Sir. I've removed the following task:" + "\n" + task +
                "\n" + "Now you have " + totalTasks + " task(s) in the list.";
        return response;
    }

    /**
     * Displays a message when there are no matching tasks found.
     *
     * @return A message indicating no matching tasks found.
     */
    public String showNoMatchingTasksMessage() {
        return "No matching tasks found in your list :(";
    }

    /**
     * Displays a message when there are matching tasks found.
     *
     * @return A message indicating matching tasks found.
     */
    public String showFindTaskMessage() {
        return "Here are the matching tasks in your list :D";
    }

    /**
     * Displays a message when no task is found for a given number.
     *
     * @return A message indicating no task found for a given number.
     */
    public String showNoTaskFound() {
        return "There is no task for this number!";
    }

    /**
     * Displays an error message when loading tasks from a data file fails.
     */
    public void showLoadingError() {
        System.err.println("Error! Cannot load tasks from data file.");
    }

    /**
     * Closes the scanner used for user input.
     */
    public void closeScanner() {
        scanner.close();
    }

    /**
     * Displays a message for an invalid command input.
     *
     * @return A message indicating an invalid command.
     */
    public String showInvalidCommand() {
        return "You inputted an invalid command! Please try deadline, todo, or event :)";
    }
}