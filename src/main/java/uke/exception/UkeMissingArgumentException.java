package uke.exception;

/**
 * Represents a Uke exception when a command is entered without necessary argument(s).
 */
public class UkeMissingArgumentException extends UkeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("Error: Command is missing argument(s)!");
    }

}
