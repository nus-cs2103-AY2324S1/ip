package duke;

public class DukeException extends Exception {

    /**
     * Signals an error caused by invalid inputs from user
     */
    public DukeException(String message) {
        super(message);
    }
}
