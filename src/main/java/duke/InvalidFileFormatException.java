package duke;

/**
 * The `InvalidFileFormatException` class is a custom exception that is thrown when an invalid file format is encountered while loading data in the Duke application.
 * This exception is used to handle cases where the data file has an unexpected or corrupted format.
 */
public class InvalidFileFormatException extends Exception{

    /**
     * Constructs a new `InvalidFileFormatException` with the specified detail message.
     *
     * @param message The detail message indicating the reason for the exception.
     */
    public InvalidFileFormatException(String message) {
        // Call the constructor of the parent class (Exception) and provide the error message.
        super(message);
    }
}
