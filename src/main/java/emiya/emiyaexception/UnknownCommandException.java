package emiya.emiyaexception;

/**
 * An exception that is thrown when an unknown command is given by the user.
 */
public class UnknownCommandException extends EmiyaException{
    public UnknownCommandException() {
        super("-----------------------------------------\n" +
                "Unknown command received! Please try again!\n"
                + "-----------------------------------------\n");
    }
}
