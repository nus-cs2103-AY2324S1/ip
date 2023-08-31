package adam.exception;

/**
 * This exception is for inputting the /from and /to command incorrectly.
 */
public class EventsException extends AdamException {
    @Override

    public String getInfo() {
        return "OOPS!!! You need to add one /from and one /to adam.command";
    }
}
