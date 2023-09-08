package dude.exception;

public class TaskDescriptionMissingException extends DudeException {
    public TaskDescriptionMissingException() {
        super("Please specify a task description.");
    }
}
