public class EmptyBodyException extends DukeException {

  EmptyBodyException() {
    super("    OOPS!!! The description of a todo cannot be empty.");
  }

}
