package geraldbot.exception;

public class DukeEmptyParametersException extends DukeException {
    public DukeEmptyParametersException() {
        super("☹ OOPS!!! I'm sorry, but you did not input a specific date/time for the Deadline/Event task! :-(");
    }
}
