package emiya.emiyaexception;


/**
 * An exception that is thrown when the user uses a deadline command but does not input in task details.
 */
public class EmptyDeadlineException extends EmiyaException {
    public EmptyDeadlineException() {
        super("-----------------------------------------\n"
                + "Oh no! Deadline tasks cannot be empty! Please try again!\n"
                + "-----------------------------------------\n");
    }
}
