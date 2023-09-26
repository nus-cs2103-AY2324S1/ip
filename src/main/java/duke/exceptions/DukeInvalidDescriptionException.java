package duke.exceptions;

public class DukeInvalidDescriptionException extends DukeException{
    public DukeInvalidDescriptionException() {
        super("While your command is correct, I simply can't make sense of the rest of it.");
    }
}
