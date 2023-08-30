package alpha;
// Exception thrown when using an invalid index to mark, delete or unmark a task
public class InvalidIndexException extends AlphaException{
    public InvalidIndexException(String errorMessage) {
        super(errorMessage);
    }
}
