package Exception;

public class MissingIndexException extends DukeException {
    public MissingIndexException(String command) {
        super(command + " needs an index after it...");
    }
}