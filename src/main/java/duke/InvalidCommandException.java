package duke;

public class InvalidCommandException extends DukeException {

  InvalidCommandException() {
    super("    OOPS!!! I'm sorry, but I don't know what that means :-(");
  }

}
