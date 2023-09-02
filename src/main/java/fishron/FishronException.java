package fishron;

/**
 * Custom exception class for the Fishron application.
 */
class FishronException extends Exception {
    /**
     * Constructs a new FishronException with the specified detail message.
     *
     * @param message The detail message.
     */
    public FishronException(String message) {
        super(message);
    }
}
