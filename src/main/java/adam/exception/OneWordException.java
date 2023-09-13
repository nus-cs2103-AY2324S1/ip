package adam.exception;

/**
 * This exception is for when you typed in bye and list and follow up those commands with something else.
 */
public class OneWordException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! Type in the first word you just entered";
    }
}
