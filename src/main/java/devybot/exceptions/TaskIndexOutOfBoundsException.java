package devybot.exceptions;

/**
 * Signals that an attempt to access a task at an index that does not exist has
 * occurred in the DevyBot application.
 */
public class TaskIndexOutOfBoundsException extends DevyBotException {

    /**
     * Constructs a new TaskIndexOutOfBoundsException with a specific task index.
     *
     * @param index the index of the task that does not exist
     */
    public TaskIndexOutOfBoundsException(int index) {
        super("☹ OOPS!!! The task index " + (index + 1) + " does not exist.");
    }
}
