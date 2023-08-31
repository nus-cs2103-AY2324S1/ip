package duke.exceptions;

/**
 * Represents an Duke.Exceptions.UnknownTimeException.
 *
 * @author Rayson
 */
public class UnknownTimeException extends Exception {

    public UnknownTimeException(String message) {
        super(String.format("☹ OOPS!!! Sorry but that what is that time? %s is missing it", message));
    }
}
