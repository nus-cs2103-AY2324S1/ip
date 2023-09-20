package exceptions;

/**
 * The `WoofInvalidCommandException` class represents a custom exception specific to the Woof application.
 * It is used to indicate that an invalid command has been encountered.
 */
public class WoofInvalidCommandException extends WoofException {
    /**
     * Constructs a new `WoofInvalidCommandException` with the specified error message.
     *
     * @param message The error message describing the invalid command.
     */
    public WoofInvalidCommandException(String message) {
        super(String.format("command bad ... %s", message));
    }
}
