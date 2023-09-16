package sae.exceptions;

/**
 * Custom exception class representing an error when the deadline command format is invalid.
 */
public class InvalidDeadlineException extends SaeException {

    /**
     * Returns a string representation of the exception message.
     *
     * @return A message indicating that the deadline command should be followed by a description and /by.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The deadline command should be followed by a description and /by.";
    }
}
