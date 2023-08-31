package seedu.duke;

/**
 * Encapsulates the exception where the saved data in duke.txt is not formatted correctly.
 */
public class InvalidDataFormatException extends DukeException{
    /**
     * Creates an InvalidDataFormatException.
     */
    public InvalidDataFormatException() {
        super("____________________________________________________________\n" +
                " ☹ OOPS!!! The saved data format is invalid!\n" +
                "____________________________________________________________");
    }
}
