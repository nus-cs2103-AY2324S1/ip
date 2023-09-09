package duke;

import java.util.Scanner;

/**
 * The `Ui` class handles user interface interactions and provides methods
 * for displaying messages and reading user input.
 */
public class Ui {

    /**
     * A horizontal line used for separating messages.
     */
    public static String horizontalLine = "_".repeat(60) + "\n";

    /**
     * Displays a greeting message when the program starts.
     */
    public static void greet() {
        System.out.println("Hello! I'm Bot\n"
                + "What can I do for you?\n" + horizontalLine);
    }

    /**
     * Displays a farewell message when the program exits.
     */
    public static void sayBye() {
        System.out.println(horizontalLine
                + "Bye. Hope to see you again soon!\n" + horizontalLine);
    }

    /**
     * Displays an error message.
     *
     * @param errorMessage The error message to be displayed.
     */
    public static void showError(String errorMessage) {
        System.err.println(errorMessage);
    }

    /**
     * Reads a command from the user via the provided Scanner.
     *
     * @param sc The Scanner object for reading user input.
     * @return The user's input as a String.
     */
    public static String readCommand(Scanner sc) {
        return sc.nextLine();
    }
}
