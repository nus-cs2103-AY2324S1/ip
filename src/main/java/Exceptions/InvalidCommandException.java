package Exceptions;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("Invalid command entered. See 'help' for a list of commands you can enter");
    }
}
