package cyrus.commands;

public class CommandError extends Exception {
  public CommandError(String reason) {
    super(reason);
  }
}
