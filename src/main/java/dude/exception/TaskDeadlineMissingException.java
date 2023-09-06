package dude.exception;

public class TaskDeadlineMissingException extends DudeException {
  public TaskDeadlineMissingException() {
    super("Please specify a task deadline after the task\ndescription, prefixed by `/by`.");
  }
}
