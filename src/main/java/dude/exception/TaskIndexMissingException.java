package dude.exception;

/**
 * Exception for missing task index in command.
 */
public class TaskIndexMissingException extends DudeException {
  public TaskIndexMissingException() {
    super("Please specify a task number.");
  }
}
