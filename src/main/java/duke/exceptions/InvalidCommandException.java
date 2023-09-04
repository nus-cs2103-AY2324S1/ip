package duke.exceptions;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Sorry, I don't understand what you mean ><");
    }
}
