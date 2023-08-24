/**
 * Exception for input belong to Deadlines does not have /by
 */
public class DeadlineByNotFoundException extends Exception{
    /**
     * The constructor
     */
    public DeadlineByNotFoundException() {
        super("OOPS! The description of deadline does not contain /by");
    }
}
