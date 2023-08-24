/**
 * Exception for input belong to Event does not have /From
 */
public class EventToNotFoundException extends Exception{
    /**
     * The constructor
     */
    public EventToNotFoundException() {
        super("OOPS! The description of Event does not contain /to");
    }
}

