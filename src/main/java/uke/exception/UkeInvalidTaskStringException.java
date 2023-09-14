package uke.exception;

/**
 * Represents a Uke exception when task file contains an unreadable task.
 */
public class UkeInvalidTaskStringException extends UkeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return "Error: Unable to read task in text file!";
    }
}
