/**
 * Exception for input belong to Deadlines does not have name
 */
public class DeadlineEmptyNameException extends Exception{
    /**
     * The constructor
     */
    public DeadlineEmptyNameException() {
        super("OOPS! The description of deadline cannot be empty");
    }
}
