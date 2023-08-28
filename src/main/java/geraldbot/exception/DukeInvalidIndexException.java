package geraldbot.exception;

public class DukeInvalidIndexException extends DukeException {
    public DukeInvalidIndexException(Integer size) {
        super("☹ OOPS!!! I'm sorry, but index is invalid! There are " + size + " tasks in the list! :-(");
    }
}
