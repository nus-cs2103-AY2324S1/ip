package Exceptions;

public class NoDescriptionException extends DukeException {
    public NoDescriptionException() {
        super("No description found. Please enter a description after your command.");
    }
}
