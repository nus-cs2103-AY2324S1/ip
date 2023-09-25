package ben;

/**
 * Throws error when description of a task is empty.
 */
class EmptyDescriptionException extends Exception {
    /**
     * Takes is a message.
     *
     * @param message The message of the error.
     */
    public EmptyDescriptionException(String message) {
        super(message);
    }
}
