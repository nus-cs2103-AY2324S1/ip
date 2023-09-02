package emiya.emiyaexception;


/**
 * An exception that is thrown when the user does not include /by in their deadline command.
 */
public class NoByException extends EmiyaException {
    public NoByException() {
        super("-----------------------------------------\n"
                + "It seems like there's an error in your input! Did you remember to use /by in your input?\n"
                + "-----------------------------------------\n");
    }
}

