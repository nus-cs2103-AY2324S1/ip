/**
 * Exception for input belong to Events does not have name
 */
public class EventEmptyNameException extends Exception{
    /**
     * The constructor
     */
    public EventEmptyNameException() {
        super("OOPS! The description of event cannot be empty");
    }
}