package uke.exception;

/**
 * Represents a Uke exception when a wrongly formatted date and time is entered.
 */
public class UkeInvalidDateTimeException extends UkeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return "Error: Enter valid date and time in the format DD/MM/YYYY HH:MM.";
    }
}
