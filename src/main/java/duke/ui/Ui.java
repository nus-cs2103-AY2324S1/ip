package duke.ui;

import java.util.Scanner;

/**
 * Represents the user interface of the application.
 */
public class Ui {
    private static final Scanner scanner = new Scanner(System.in);

    /**
     * Prints the welcome message to the user.
     */
    public static void showWelcomeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm duke.main.Duke\n What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the goodbye message to the user.
     */
    public static void showGoodByeMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public static void showMessage(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints an error message to the user.
     *
     * @param error The error message to be printed.
     */
    public static void showErrorMessage(String error) {
        showMessage("☹ OOPS!!! " + error);
    }

    /**
     * Retrieves the next line of user input.
     *
     * @return The next line of user input.
     */
    public static String getUserInput() {
        return scanner.nextLine();
    }
}
