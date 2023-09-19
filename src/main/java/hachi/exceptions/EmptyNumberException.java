package hachi.exceptions;

/**
 * Exception thrown when the task number is not specified for "delete", "mark", or "unmark"
 * commands.
 */
public class EmptyNumberException extends HachiException {
    /**
     * Constructor for the EmptyNumberException class.
     * @param check The type of action the user wanted to perform on the task.
     *              Either "delete", "mark", or "unmark".
     */
    public EmptyNumberException(String check) {
        super(String.format("☹ OOPS!!! Please indicate which task you wish to %s", check));
    }
}
