package bot.exceptions;

/**
 * Exception for when the task list is empty and a function requires an
 * item in the task list.
 */
public class EmptyListException extends BotException {
    /**
     * Default constructor.
     */
    public EmptyListException() {
        super("Oops, there are no tasks in your list!");
    }
}
