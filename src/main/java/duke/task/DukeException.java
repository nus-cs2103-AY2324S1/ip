package duke.task;

/**
 * DukeException is the class that is thrown when an unexpected user input is met
 */
public class DukeException extends Exception {
    /**
     * field message would be the custom message for each type of error
     */
    private final String message;

    /**
     * @param message custom message for each type of error
     */
    public DukeException(String message) {
        super(message);
        this.message = message;
    }

    /**
     * @return String format representation of the DukeException
     */
    @Override
    public String toString() {
        return "MEEEOOWWWWWW!!!!" + this.message;
    }

}
