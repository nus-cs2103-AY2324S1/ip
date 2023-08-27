package duke;

public class InvalidDateException extends DukeException {
  public InvalidDateException() {
    super("    Invalid date format, enter the dates using: /from dd/MM/yyyy /to dd/MM/yyyy or /by dd/MM/yyyy");
  }
}
