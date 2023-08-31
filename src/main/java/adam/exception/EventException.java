package adam.exception;

/**
 * This exception is used for event commands that have incorrect use of /from and /to.
 */
public class EventException extends AdamException {
    @Override
    public String getInfo() {
        return "OOPS!!! You need to add one /from and one /to command";
    }
}
