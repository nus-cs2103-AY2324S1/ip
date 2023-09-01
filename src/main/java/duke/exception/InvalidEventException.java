package duke.exception;

public class InvalidEventException extends DukeException {
    public InvalidEventException() {
        super("Oh no! It seems like you have indicated an invalid event :/ \n"
                + "Please follow this format:\n"
                + "event <task name> /from <yyyy-mm-dd> <HHmm> /to <yyyy-mm-dd> <HHmm>"
        );
    }
}
