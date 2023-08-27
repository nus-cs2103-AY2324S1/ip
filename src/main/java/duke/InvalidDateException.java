package duke;

public class InvalidDateException extends DukeException {
  public InvalidDateException() {
    super("    Invalid date format, enter the dates in a dd/MM/yyyy format");
  }
}
