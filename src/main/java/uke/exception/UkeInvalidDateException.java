package uke.exception;

/**
 * Represents a Uke exception when a wrongly formatted date is entered.
 */
public class UkeInvalidDateException extends UkeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return "Error: Enter valid date in the format DD/MM/YYYY.";
    }
}
