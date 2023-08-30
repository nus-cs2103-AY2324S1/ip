package cyrus.commands;

/**
 * Custom error that occurs when a {@code Command} executes.
 *
 * <p>Often occurs when the input is parsed wrongly.</p>
 */
public class CommandError extends Exception {
  public CommandError(String reason) {
    super(reason);
  }
}
