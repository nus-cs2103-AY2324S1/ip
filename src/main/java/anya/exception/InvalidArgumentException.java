package anya.exception;

/**
 * Custom exception class for handling invalid arguments in Anya commands.
 *
 * This class extends the {@code AnyaException} class to create custom exceptions specifically for cases where
 * invalid arguments are provided to Anya commands. It allows for the propagation of error messages indicating
 * that an invalid argument was detected.
 *
 * @see AnyaException
 */
public class InvalidArgumentException extends AnyaException {
    /**
     * Constructs a new instance of the {@code InvalidArgumentException} class with the specified error message.
     *
     * @param message A descriptive error message explaining the invalid argument condition.
     */
    public InvalidArgumentException(String message) {
        super(message);
    }
}
