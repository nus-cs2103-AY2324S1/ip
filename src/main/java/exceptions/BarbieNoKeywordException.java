package exceptions;

/**
 * Represents a missing keyword error
 */
public class BarbieNoKeywordException extends BarbieException {
    /**
     * Represents a Barbie exception when there is no keyword associated with the 'find' command.
     */
    public BarbieNoKeywordException() {
        super("Hmm.. there didn't seem to be a keyword with that 'find' command, \nmake sure to find by typing "
                + "'find <keywaord>'!");
    }
}
