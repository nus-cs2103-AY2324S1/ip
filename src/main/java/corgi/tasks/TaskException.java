package corgi.tasks;

/**
 * Custom exception class to handle errors related to Task class.
 */
public class TaskException extends Exception{
    /**
     * Constructs a new ParsingException with the specified error message.
     *
     * @param msg The error message describing the parsing error.
     */
    public TaskException(String msg) {
        super(msg);
    }
}
