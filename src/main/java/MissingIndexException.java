// Exception thrown when mark or unmark is called without an index
public class MissingIndexException extends AlphaException{
    public MissingIndexException(String errorMessage) {
        super(errorMessage);
    }
}
