package duke.duke;

/**
 * Represents the Ui that deals with the interactions with the user.
 */
public class Ui {

    /**
     * Displays the standard welcome message.
     */
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Victor\n"
                + "What can I do for you?\n----------\n");
    }

    /**
     * Displays the standard goodbye message.
     */
    public void showByeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Displays the line.
     */
    public void showLine() {
        System.out.println("----------\n");
    }

    /**
     * Displays the message given by the input.
     * @param message Message displayed.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }

}
