package duke.ui;

/**
 * Provides methods to store messages, and errors.
 */
public class Ui {
    private String output;

    /**
     * Stores a message by the chatbot.
     *
     * @param message The message to be displayed.
     */
    public void sendMessage(String message) {
        this.output = message;
    }

    /**
     * Stores an error message by the chatbot.
     *
     * @param message The error message to be displayed.
     */
    public void showError(String message) {
        this.output = message;
    }

    /**
     * Returns a message to the user.
     *
     * @return The message to be displayed.
     */
    public String getOutput() {
        return this.output;
    }
}
