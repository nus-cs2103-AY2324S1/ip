package duke;

public class DukeException extends Exception {
  public DukeException(String msg) {
      super(msg);
  }

  @Override
  public String toString() {
      return " ☹ OOPS!!! " + super.getMessage();
  }
}
