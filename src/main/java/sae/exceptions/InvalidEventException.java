package sae.exceptions;

/**
 * Custom exception class representing an error when the event command format is invalid.
 */
public class InvalidEventException extends SaeException {

    /**
     * Returns a string representation of the exception message.
     *
     * @return A message indicating that the event command should be followed by a description, /from, and /to.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The event command should be followed by a description, /from, and /to.";
    }
}
