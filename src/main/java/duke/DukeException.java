package duke;

/**
 * Special exceptions encountered by the chatbot.
 */
class DukeException extends Exception {
    /**
     * Constructor for the DukeException class.
     * @param errorMessage Message about the error.
     */
    public DukeException(String errorMessage) {
        super(errorMessage);
    }
}
