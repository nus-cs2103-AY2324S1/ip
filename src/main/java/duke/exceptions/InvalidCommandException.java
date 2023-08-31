package duke.exceptions;

public class InvalidCommandException extends DukeException{

    public InvalidCommandException() {
        super("I don't know what you meant!");
    }
    
}
