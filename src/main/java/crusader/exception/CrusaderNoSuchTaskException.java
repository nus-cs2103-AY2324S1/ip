package crusader.exception;

/**
 * Represents an error dur to accessing a nonexistent task
 */
public class CrusaderNoSuchTaskException extends CrusaderException {
    public CrusaderNoSuchTaskException(String message) {
        super(message);
    }
}
