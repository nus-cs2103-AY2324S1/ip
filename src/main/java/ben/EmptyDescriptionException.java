package ben;

/**
 * Error thrown when description of a task is empty.
 */
class EmptyDescriptionException extends Exception {
    /**
     * Constructor that takes is a message.
     *
     * @param message The message of the error.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
