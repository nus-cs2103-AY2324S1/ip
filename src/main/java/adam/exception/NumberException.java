package adam.exception;

/**
 * This exception is for when using an edit command incorrectly.
 */
public class NumberException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to follow this command by a number";
    }
}
