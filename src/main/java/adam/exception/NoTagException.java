package adam.exception;

/**
 * This exception is thrown when user tries to tag a task without a tag description.
 */
public class NoTagException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to add a tag to this task";
    }
}
