package duke;

/**
 * The Ui class handles user interactions and displays messages to the user.
 */
public class Ui {
    /**
     * The application's logo.
     */
    private static String logo = "Geck";

    /**
     * Displays a welcome message to the user.
     *
     * @return The welcome message.
     */
    public static String showWelcomeMessage() {
        showLine();
        return "Hello Class! " + logo
                + ", Ms Frizzle's pet here!\nWhat adventure shall we go on today?";
    }
    /**
     * Displays a line separator.
     */
    public static void showLine() {
        System.out.println("--------------------------");
    }

    /**
     * Displays a loading error message to the user.
     *
     * @param e The DukeException that occurred during loading.
     * @return The loading error message.
     */
    public static String showLoadingError(DukeException e) {
        System.out.println(e.toString());
        return e.getMessage();
    }


    /**
     * Displays a goodbye message to the user.
     */
    public static void showByeMessage() {
        showLine();
        System.out.println("Bye Class!");
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
        System.out.println("☹ Sorry class! OOPS!!! " + errorMessage);
    }
}
