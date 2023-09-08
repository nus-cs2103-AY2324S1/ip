package dude.exception;

/**
 * Exception for add task command with missing description.
 */
public class TaskDescriptionMissingException extends DudeException {
    public TaskDescriptionMissingException() {
        super("Please specify a task description.");
    }
}
