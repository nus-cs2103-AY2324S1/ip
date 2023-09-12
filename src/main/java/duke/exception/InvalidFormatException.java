package duke.exception;

/**
 * Represents an exception that occurs when the format of a command is invalid.
 */
public class InvalidFormatException extends DukeException {

    /**
     * Constructs an InvalidFormatException with an error message explaining the invalid command format.
     *
     * @param formatDescription A description of the expected format of the command.
     */
    public InvalidFormatException(String formatDescription) {
        super(" ☹ OOPS!!! The format of the command is invalid. \n" + formatDescription);
    }
}
