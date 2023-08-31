package adam.exception;

/**
 * This exception is for when Tasks do not have descriptions.
 */
public class NoDescriptionException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to add a description to these adam.tasks";
    }
}
