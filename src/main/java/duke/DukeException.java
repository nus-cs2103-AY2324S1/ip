package duke;

/**
 * Custom exception class to handle exceptions with Duke
 */
public class DukeException extends Exception {
    public DukeException(String input) {
        super(input);
    }
}
