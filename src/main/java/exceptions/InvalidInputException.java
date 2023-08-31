package exceptions;

/**
 * The InvalidInputException class is a custom exception that is thrown when the user provides an invalid input to the program.
 */
public class InvalidInputException extends DukeException {

    /**
     * Constructs an InvalidInputException with the specified error message.
     *
     * @param msg The error message associated with the exception.
     */
    public InvalidInputException(String msg) {
        super(msg);
    }

    /**
     * Returns a user-friendly error message indicating that the input provided is invalid.
     *
     * @return A formatted error message.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
