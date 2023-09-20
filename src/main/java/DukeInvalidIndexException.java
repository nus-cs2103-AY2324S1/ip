public class DukeInvalidIndexException extends DukeException {
    public DukeInvalidIndexException(int taskIndex) {
        super("OOPS!!! No such task with index " + taskIndex);
    }
}
