class DuchessException extends Throwable {
    private String message;

    /**
     * Creates a new DuchessException with the given message.
     *
     * @param message - the message for the DuchessException.
     */
    DuchessException(String message) {
        this.message = message;
    }

    /**
     * Returns the message of this exception.
     *
     * @return the message for this Exception.
     */
    @Override
    public String getMessage() {
        return this.message;
    }
}
