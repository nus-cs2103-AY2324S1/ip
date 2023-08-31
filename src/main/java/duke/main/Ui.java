package duke.main;

import duke.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions with the user interface, displaying messages
 * and receiving user input.
 */

public class Ui {
    private Scanner sc;

    /**
     * Constructor for the Ui class. Initializes the scanner and displays the Chatbot logo.
     */
    Ui() {
        sc = new Scanner(System.in);
        String logo =
                " ____        _        \n"
                        + "|  _ \\ _   _| | _____ \n"
                        + "| | | | | | | |/ / _ \\\n"
                        + "| |_| | |_| |   <  __/\n"
                        + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n " + logo);


    }

    /**
     * Displays the welcome message to the user.
     */
    public void showWelcome() {
        showLine();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?\n");

    }

    /**
     * Displays a divider line.
     */
    public void showLine() {
        System.out.println("_______________________________________________________________\n");
    }

    /**
     * Displays an error message to the user.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        System.out.println(message);
    }

    /**
     * Displays a completion message after adding a task.
     *
     * @param task The task that was added.
     * @param size The total number of tasks after adding the new task.
     */
    public void displayCompletionMessage(Task task , int size) {
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + size + " tasks in the list.");
    }

    /**
     * Displays a message along with a task.
     *
     * @param message The message to be displayed.
     * @param task The task to be displayed.
     */
    public void printMessage(String message, Task task) {
        System.out.println("\t" + message + task.toString());
    }

    /**
     * Displays the exit message to the user.
     */
    public void printExitMessage() {
        System.out.println("\tBye. Hope to see you again soon!");
    }

    /**
     * Displays the list of tasks to the user.
     *
     * @param tasks The list of tasks to be displayed.
     */
    public void printTaskList(ArrayList<Task> tasks, String message) {
        System.out.println(message);
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + tasks.get(i).toString());
        }
    }

    /**
     * Displays a message after removing a task.
     *
     * @param removedTask The task that was removed.
     * @param size The total number of tasks after removing the task.
     */
    public void printDeleteMessage(Task removedTask, int size){
        System.out.println("Noted. I've removed this task:\n\t" + removedTask);
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    /**
     * Reads a command entered by the user.
     *
     * @return The user's command as a String.
     */
    String readCommand() {
        return sc.nextLine();
    }

}
