package uke.exception;

/**
 * Represents a Uke exception when any error occurs related to the data file.
 */
public class UkeDataFileException extends UkeException {

    /**
     * Returns the string representation of the exception.
     */
    @Override
    public String toString() {
        return String.format("Error: Data file.");
    }

}
