package duke;

/**
 * The Ui class handles user interactions and displays messages to the user.
 */
public class Ui {
    private static String logo = "Wiz";
    /**
     * Displays a welcome message to the user.
     */
    public static String showWelcomeMessage() {
        showLine();
        return "Hello from " + logo
                + "\nWhat can I do for you?";
    }
    /**
     * Displays a line separator.
     */
    public static void showLine() {
        System.out.println("--------------------------");
    }

    /**
     * Displays a loading error message to the user.
     */
    public static String showLoadingError(DukeException e) {
        System.out.println(e);
        return e.getMessage();
    }


    /**
     * Displays a goodbye message to the user.
     */
    public static void showByeMessage() {
        showLine();
        System.out.println("Bye!");
    }
    public static void showMessage(String result) {
        System.out.println(result);
    }

    /**
     * Displays an error message to the user.
     *
     * @param errorMessage The error message to display.
     */
    public static void showError(String errorMessage) {
        System.out.println("☹ OOPS!!! " + errorMessage);
    }
}
