package dude.exception;

/**
 * Exception for add deadline command with missing deadline.
 */
public class TaskDeadlineMissingException extends DudeException {
  public TaskDeadlineMissingException() {
    super("Please specify a task deadline after the task\ndescription, prefixed by `/by`.");
  }
}
