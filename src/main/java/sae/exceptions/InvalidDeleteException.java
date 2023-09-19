package sae.exceptions;

/**
 * Custom exception class representing an error when delete command is invalid.
 */
public class InvalidDeleteException extends SaeException {

    /**
     * Returns a string representation of the exception message.
     *
     * @return A message indicating that the delete command failed.
     */
    @Override
    public String toString() {
        return "☹ OOPS!!! The delete command failed. Use the list command to check if the task exists.";
    }
}
