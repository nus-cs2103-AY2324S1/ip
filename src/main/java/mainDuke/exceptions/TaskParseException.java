package mainDuke.exceptions;

/**
 * Represents a parsing error between String and Task
 */
public class TaskParseException extends Exception {
    /**
     * public onstructor, returns a <code>TaskParseException</code> instance
     * @param message message that is to be displayed
     */
    public TaskParseException(String message) {
        super(message);
    }
}
