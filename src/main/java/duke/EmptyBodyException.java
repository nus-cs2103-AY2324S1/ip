package duke;

public class EmptyBodyException extends DukeException {

  EmptyBodyException() {
    super("    OOPS!!! The description of a task cannot be empty.");
  }

}
