package dude.exception;

public class InvalidSortByOrderException extends DudeException {
    public InvalidSortByOrderException() {
        super("I don't know how to sort that way. (Available options: asc [ascending], desc [descending])");
    }
}
