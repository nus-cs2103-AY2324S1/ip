public class DukeMissingValueException extends DukeException{
    public DukeMissingValueException(String value, String command) {
        super(String.format("OOPS!!! The %s of %s cannot be empty.", value, command));
    }
}
