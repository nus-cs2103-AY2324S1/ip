package duke;

/**
 * Signals that given data does not fulfill some requirements.
 */
public class IncompleteInputException extends Exception {

    public IncompleteInputException(String type) {
        super("☹ OOPS!!! The description of a " + type + " cannot be empty.");
    }
}
