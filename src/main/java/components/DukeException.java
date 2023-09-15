package components;

import tasks.Task;
import tasks.TaskList;

/**
 * Represents an exception specific to the Duke application.
 */
public class DukeException extends Exception {

    /**
     * Constructor for DukeException.
     *
     * @param message Error message.
     */
    public DukeException(String message) {
        super(message);
    }

    /**
     * Return error message.
     *
     * @return Error message.
     */
    @Override
    public String toString() {
        return String.format("😭 OOPS!!! %s", super.getMessage());
    }

    public static class DuplicateDescriptionException extends DukeException {
        public DuplicateDescriptionException(Task oldTask) {
            super((String.format("You already have a task with the same description in your list! \n\n %s" +
                    "\n\n" + "If you would still want to proceed with adding your new task, type yes. If something else is typed, " +
                    "the task will be discarded", oldTask)));
        }
    }

    public static class InvalidCommandException extends DukeException {
        public InvalidCommandException() {
            super("I'm sorry, but I don't know what that means :-(");
        }
    }
}
