package sae.exceptions;

/**
 * Custom exception class representing an error when the unmark command is invalid.
 */
public class InvalidUnMarkException extends SaeException {

    /**
     * Returns a string representation of the exception message.
     *
     * @return A message indicating that the unmark command failed.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! I wasn't able to unmark the task. Use the list command to check if the task exists.";
    }
}
