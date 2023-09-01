package duke;

/**
 * Custom exception class that is used to represent errors when the chatbot runs.
 */
public class DukeException extends Exception {
    public DukeException(String msg) {
        super(msg);
    }
}
