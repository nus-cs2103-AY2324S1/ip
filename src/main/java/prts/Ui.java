package prts;

import java.util.Scanner;

/**
 * The class responsible for all interaction with the user. User input and almost all program
 * output is managed by this class.
 */
public class Ui {

    private static final String SOFTBREAK = "--------------------------------------------------";
    private static final String HARDBREAK = "——————————————————————————————————————————————————";
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the initial message on startup.
     */
    public void showWelcome() {
        System.out.println(HARDBREAK
                + "\n...Fingerprint match found. Verification complete. Welcome home.\n"
                + "PRTS, at your service. What would you like to do today?\n" + HARDBREAK);
    }

    /**
     * Displays the farewell message upon exiting the program.
     */
    public void showGoodbye() {
        System.out.println("Farewell. See you again soon.\n" + HARDBREAK);
    }

    /**
     * Reads in the next line of user input.
     * @return The raw user input, as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Displays a dashed line.
     */
    public void showSoftLine() {
        System.out.println(SOFTBREAK);
    }

    /**
     * Displays a solid line.
     */
    public void showHardLine() {
        System.out.println(HARDBREAK);
    }

    /**
     * Lists the current Tasks stored in the TaskList.
     * @param tasks The current state of the TaskList.
     */
    public void listTasks(TaskList tasks) {

        System.out.println("Here are your tasks for today.");

        System.out.println(tasks);

        System.out.println("You now have " + tasks.getSize() + " tasks in your list.");

    }

    /**
     * Displays a given message to the user.
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

}
