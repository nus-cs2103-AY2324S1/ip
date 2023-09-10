package duke.exception;

/**
 * Exception thrown when an argument is left empty.
 */
public class EmptyArgException extends DukeException {

    /**
     * Constructs EmptyArgException.
     *
     * @param arg The name of the argument in question.
     */
    public EmptyArgException(String arg) {
        super(String.format("☹ OOPS!!! %s field should not be empty.", arg));
    }

}
