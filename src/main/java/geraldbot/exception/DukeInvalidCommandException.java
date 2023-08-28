package geraldbot.exception;

public class DukeInvalidCommandException extends DukeException {
    public DukeInvalidCommandException(String command) {
        super("☹ OOPS!!! The description of a " + command + " cannot be empty.");
    }

    public DukeInvalidCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
