package duke;

/**
 * Encapsulates some exceptions that are specific to adding tasks on the Duke bot.
 */
public class DukeException extends Exception {
    /**
     * Constructor for a new Duke Exception.
     *
     * @param message message to be displayed to the user.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Prints the message of the DukeException.
     */
    public void printException() {
        System.out.println("-----------------------------------------------");
        System.out.println(this.getMessage());
        System.out.println("-----------------------------------------------");
    }
}
