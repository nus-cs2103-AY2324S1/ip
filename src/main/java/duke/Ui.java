package duke;

import java.util.Scanner;

/**
 * duke.UI is a class that handles interactions with the user.
 * It is responsible for displaying messages, and reading user input.
 */
public class Ui {
    /**
     * Constructor for UI class
     */
    public Ui() {

    }

    /**
     * Display Welcome Message.
     */
    public void showWelcome() {
        System.out.println("Hello! I'm Chatty\n" + "What can I do for you?");
    }

    /**
     * Displays a given message to the user.
     *
     * @param msg The message to be displayed.
     */
    public void showMessage(String msg) {
        System.out.println(msg);
    }

    /**
     * Reads user's input.
     *
     * @return The user's input as a String.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput;
    }

    /**
     * Displays an error message indicating that loading a file has failed.
     */
    public void showLoadingError() {
        System.out.println("File not found");
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The current duke.TaskList containing tasks to display.
     */
    public void showList(TaskList tasks) {
        System.out.println(tasks.toString());
    }

    /**
     * Displays a goodbye message to the user.
     */
    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}