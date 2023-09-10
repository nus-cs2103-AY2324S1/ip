package prts;

import java.util.Scanner;

/**
 * The class responsible for all interaction with the user. User input and almost all program
 * output is managed by this class.
 */
public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Displays the initial message on startup.
     */
    public String showWelcome() {
        return "\n...Fingerprint match found. Verification complete. Welcome home.\n"
                + "PRTS, at your service. What would you like to do today?\n";
    }

    /**
     * Displays the farewell message upon exiting the program.
     */
    public String showGoodbye() {
        return "Farewell. See you again soon.\n";
    }

    /**
     * Reads in the next line of user input.
     * @return The raw user input, as a string.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Lists the current Tasks stored in the TaskList.
     * @param tasks The current state of the TaskList.
     */
    public String listTasks(TaskList tasks) {
        assert tasks != null : "The tasklist cannot be null!";

        return "Here are your tasks for today." + tasks + "You now have "
                + tasks.getSize() + " tasks in your list.";

    }

    /**
     * Prints a message to System.out.
     * @param message The message to be displayed.
     */
    public void displayMessage(String message) {
        System.out.println(message);
    }

}
