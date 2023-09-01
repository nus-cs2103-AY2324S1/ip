package duke.exceptions;

/**
 * Represents the exception thrown when the user inputs a task with invalid time format.
 * The proper format is yyyy-mm-dd
 */
public class InvalidTimeFormatException extends Exception {
    public InvalidTimeFormatException(String message) {
        super(message + "\n    Proper Format: yyyy-mm-dd");
    }
}