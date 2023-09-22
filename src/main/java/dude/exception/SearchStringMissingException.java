package dude.exception;

/**
 * Exception for missing search string in find command.
 */
public class SearchStringMissingException extends DudeException {
    /**
     * Constructs new SearchStringMissingException.
     * */
    public SearchStringMissingException() {
        super("Please specify a keyword to search.");
    }
}
