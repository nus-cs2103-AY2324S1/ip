package exceptions;

public class InvalidCommandException extends DukeException {
    public InvalidCommandException() {
        super("invalid task");
    }

    @Override
    public String toString() {
        return super.toString() + " I'm sorry, but I don't know what that means :-(";
    }
}
