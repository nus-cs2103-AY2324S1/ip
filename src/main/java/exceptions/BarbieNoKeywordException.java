package exceptions;

public class BarbieNoKeywordException extends BarbieException{
    public BarbieNoKeywordException() {
        super("Hmm.. there didn't seem to be a keyword with that 'find' command, \nmake sure to find by typing 'find <keywaord>'!");
    }
}
