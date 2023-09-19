package cheems.exceptions;

/**
 * Custom exception class to represent an input with empty arguments.
 * This exception is thrown when an input with empty arguments is entered by the user.
 */
public class EmptyArgumentException extends RuntimeException {
    /**
     * Creates a new runtime exception due to empty argument for a keyword.
     * @param keyword The keyword that requires argument.
     */
    public EmptyArgumentException(String keyword) {
        super(String.format("Please specify the correct number of arguments for keyword %s ~~", keyword));
    }
}
