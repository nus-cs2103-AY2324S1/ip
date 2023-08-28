package geraldbot.exception;

public class DukeInvalidDateException extends DukeException {
    public DukeInvalidDateException() {
        super("☹ OOPS!!! The selected date/time is invalid.");
    }
}
