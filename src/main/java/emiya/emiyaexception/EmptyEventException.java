package emiya.emiyaexception;

/**
 * An exception that is thrown when the user uses an event command but does not input in task details.
 */
public class EmptyEventException extends EmiyaException {
    public EmptyEventException() {
        super("-----------------------------------------\n"
                + "Oh no! Event tasks cannot be empty! Please try again!\n"
                + "-----------------------------------------\n");
    }
}
