package dude.exception;

public class InvalidSortByTypeException extends DudeException {
    public InvalidSortByTypeException() {
        super("I don't know how to sort that way. (Available options: date, description, type)");
    }
}
