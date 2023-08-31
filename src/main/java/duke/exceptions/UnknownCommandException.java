package duke.exceptions;

/**
 * Represents an Duke.Exceptions.UnknownCommandException.
 *
 * @author Rayson
 */
public class UnknownCommandException extends Exception {

    public UnknownCommandException(String message) {
        super(String.format("☹ OOPS!!! I'm sorry, but I don't know what %s means :-(", message));
    }

}
