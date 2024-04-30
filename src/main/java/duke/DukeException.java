package duke;

/**
 * An Exception class that handles exceptions from the chatbot.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}