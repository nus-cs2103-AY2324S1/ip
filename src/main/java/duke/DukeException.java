package duke;

/**
 * Encapsulates exception for the chat bot.
 */
public class DukeException extends RuntimeException{
    public DukeException(String message) {
        super(message);
    }
}
