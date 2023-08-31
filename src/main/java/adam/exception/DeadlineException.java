package adam.exception;

/**
 * This exception is used when a user input the /by incorrectly.
 */
public class DeadlineException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to add one /by adam.command to indicate by when deadline is";
    }
}
