package duke;

// deals with interactions with the user

/**
 * Object to deal with user interaction.
 */
public class Ui {
    private String response = "";

    /**
     * Creates the Ui object.
     */
    public Ui() {
    }


    /**
     * returns the error message with some formatting.
     *
     * @param msg The error message.
     */
    public void showError(String msg) {
        this.response += "There was an error: " + msg + "\n";
    }


    /**
     * Displays the message.
     *
     * @param msg The message to display.
     */
    public void display(String msg) {
        this.response += this.response = msg + "\n";
    }

    /**
     * Returns the String that the bot is supposed to display after executing some commands.
     * Clears out the existing response once this method is called.
     */
    public String respond() {
        String res = this.response;
        this.response = "";
        return res;
    }
}
