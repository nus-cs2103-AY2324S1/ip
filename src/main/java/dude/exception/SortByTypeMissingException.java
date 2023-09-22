package dude.exception;

public class SortByTypeMissingException extends DudeException {
    public SortByTypeMissingException() {
        super("Please specify how to sort. (Available options: date, description, type)");
    }
}
