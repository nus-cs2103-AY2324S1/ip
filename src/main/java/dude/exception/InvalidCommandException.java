package dude.exception;

public class InvalidCommandException extends DudeException {
  public InvalidCommandException() {
    super(
      "I don't know what that means.\n" +
        "Try checking if you've typed the command correctly."
    );
  }
}
