package duke.exception;

/**
 * General Exception of the chat bot.
 */
public class DukeException extends Exception {
    private String message;

    /**
     * Constructor for DukeException.
     * @param message error message for the exception
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    public void printMessage() {
        System.out.println(this.message);
    }
}
