public class MissingIndexException extends DukeException{
    MissingIndexException(String command) {
        super(command + " needs an index after it...");
    }
}