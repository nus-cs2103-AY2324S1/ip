public class InvalidCommandException extends DukeException {
    public InvalidCommandException(String command) {
        super("No such command bruh: " + command);
    }
}