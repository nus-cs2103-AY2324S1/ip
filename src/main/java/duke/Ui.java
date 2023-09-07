package duke;

/**
 * The Ui class handles user interface interactions and displays messages to the user.
 */
public class Ui {

    /**
     * Displays a greeting message when the chatbot starts.
     */
    public static void greet() {
        System.out.println("---------------------------------------------\n Hello! I'm zy\n"
                + " What can I do for you?\n---------------------------------------------");
    }

    /**
     * Displays a goodbye message when the chatbot exits.
     */
    public static void goodbye() {
        System.out.println("---------------------------------------------\n Bye. Hope to see you again soon!"
                + "\n---------------------------------------------");
    }

    /**
     * Prints a horizontal line as a separator.
     */
    public static void printLine() {
        System.out.println("---------------------------------------------");
    }

    /**
     * Displays a message to the user.
     *
     * @param message The message to display.
     */
    public static void showMessage(String message) {
        System.out.println(message);
    }

    /**
     * Displays an error message when there is an issue loading data.
     *
     * @param e The DukeException containing the error message.
     * @return The error message displayed.
     */
    public static String showLoadingError(DukeException e) {
        System.out.println(e);
        return e.getMessage();
    }
}
