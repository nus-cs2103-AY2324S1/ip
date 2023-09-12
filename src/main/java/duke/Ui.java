package duke;

import java.util.Scanner;

/**
 * duke.UI is a class that handles interactions with the user.
 * It is responsible for displaying messages, and reading user input.
 */
public class Ui {

    private Scanner scanner;

    /**
     * Constructor for UI class
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
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
        return this.scanner.nextLine();
    }

    /**
     * Displays an error message indicating that loading a file has failed.
     *
     * @return The output as a String.
     */
    public String showLoadingError() {
        String fileError = "File not found";
        System.out.println(fileError);
        return fileError;
    }

    /**
     * Displays the list of tasks.
     *
     * @param tasks The current duke.TaskList containing tasks to display.
     * @return The output as a String.
     */
    public String showList(TaskList tasks) {
        String listString = tasks.toString();
        System.out.println(listString);
        return listString;
    }

    /**
     * Displays a goodbye message to the user.
     *
     * @return The output as a String.
     */
    public String showBye() {
        this.scanner.close();
        String byeMsg = "Bye. Hope to see you again soon!";
        System.out.println(byeMsg);
        return byeMsg;
    }
}
