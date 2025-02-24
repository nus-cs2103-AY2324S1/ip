package duke.utility;

/**
 * Handles interaction with the user by providing input and displaying messages.
 */
public class Ui {
    private StringBuilder output;


    /**
     * Constructs a Ui object.
     */
    public Ui() {
        this.output = new StringBuilder();
    }

    /**
     * Returns the next line of input from the user.
     *
     * @return The next line of input from the user.
     */
    public String getOutput() {
        return this.output.toString();
    }

    /**
     * Clears the output.
     */
    public void clearOutput() {
        this.output = new StringBuilder();
    }

    /**
     * Prints a message to the user.
     *
     * @param message The message to be printed.
     */
    public void printMessage(String message) {
        this.output.append(message);
    }


}
