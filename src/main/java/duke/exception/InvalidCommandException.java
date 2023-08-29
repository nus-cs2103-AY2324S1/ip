package duke.exception;

public class InvalidCommandException extends DukeException {

    public InvalidCommandException(String cmd) {
        super(String.format("☹ OOPS!!! I'm sorry, but I don't know what \"%s\" means :-(", cmd));
    }

}
