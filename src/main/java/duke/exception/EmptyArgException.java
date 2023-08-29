package duke.exception;

public class EmptyArgException extends DukeException {

    public EmptyArgException(String arg) {
        super(String.format("☹ OOPS!!! %s field should not be empty.", arg));
    }

}