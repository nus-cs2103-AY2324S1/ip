package sae.exceptions;

/**
 * Exception class representing an error when the 'find' command is followed by an empty or missing keyword.
 * This exception is used to handle invalid 'find' commands.
 */
public class InvalidFindException extends SaeException {

    /**
     * Returns a string representation of this exception.
     *
     * @return A message indicating that the 'find' command should be followed by a keyword.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The find command should be followed by a keyword.";
    }
}
