package dude.exception;

public class TaskIndexMissingException extends DudeException {
  public TaskIndexMissingException() {
    super("Please specify a task number.");
  }
}
