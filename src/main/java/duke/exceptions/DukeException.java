package duke.exceptions;

/**
 * Represents an exception that might happen in the chatbot.
 *
 * @author Andrew Daniel Janong
 */
public class DukeException extends Exception{
    public DukeException(String message) {
        super(message);
    }
}
