/**
 * Exception for input belong to Event does not have /From
 */
public class EventFromNotFoundException extends Exception{
    /**
     * The constructor
     */
    public EventFromNotFoundException() {
        super("OOPS! The description of event does not contain /from");
    }
}
