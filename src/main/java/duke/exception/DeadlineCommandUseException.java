package duke.exception;

/**
 * Represents an exception that occurs when the user input for a deadline command is invalid.
 * This exception is thrown when the '/by' part of the deadline command is missing or not followed
 * by a valid date and time.
 */
public class DeadlineCommandUseException extends Exception {

    /**
     * Constructs a DeadlineCommandUseException with a custom error message.
     *
     * @param message The error message indicating the issue with the deadline command input.
     */
    public DeadlineCommandUseException(String message) {
        super(message + ":" + " Accio error! deadline must be followed by a task to be added to the list " +
                "and a time specified after '/by' ");
    }
}
