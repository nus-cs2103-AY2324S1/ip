package duke.ui;

/**
 * Represents a Ui class that deals with the interactions with the user.
 */
public class Ui {
    private final String lineSeparator = "____________________________________________________________";

    /**
     * Prints the error message.
     *
     * @param message Error message.
     */
    public void showError(String message) {
        System.out.println(lineSeparator + "\n" + message + "\n" + lineSeparator);
    }
}
