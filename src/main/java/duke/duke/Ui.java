package duke.duke;

/**
 * Represents the Ui that deals with the interactions with the user.
 */
public class Ui {

    /**
     * Displays the standard welcome message.
     */
    public String showWelcomeMessage() {
        return ("Hello! I'm Victor\n"
                + "What can I do for you?\n----------\n");
    }

    /**
     * Displays the message given by the input.
     * @param message Message displayed.
     */
    public String showMessage(String message) {
        return (message);
    }

}
