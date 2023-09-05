package duke.exception;

/**
 * Represents an exception that occurs when the user input for an event command is invalid.
 * This exception is thrown when the '/from' or '/to' parts of the event command are missing or
 * not followed by valid date and time.
 */
public class EventCommandUseException extends Exception{

    /**
     * Constructs an EventCommandUseException with a custom error message.
     *
     * @param message The error message indicating the issue with the event command input.
     */
    public EventCommandUseException(String message) {
        super(message + ":" + " Accio error! Invalid input as when the event starts " +
                "must be specified after '/from'. When the event ends must also be specified after '/to'.");
    }
}
