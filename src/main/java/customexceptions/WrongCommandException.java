package customexceptions;

/**
 * The <code>WrongCommandException</code> class represents an exception that is thrown
 * when an invalid command is provided.
 *
 * This exception is typically thrown when a user inputs a command that is not recognized
 * by the application. It extends the standard Java <code>Exception</code> class.
 *
 * @see Exception
 */
public class WrongCommandException extends Exception {

    /**
     * Constructs a new <code>WrongCommandException</code> with the specified command.
     *
     * @param command The invalid command that triggered the exception.
     */
    public WrongCommandException(String command) {
        super(command + " is not a proper command! "
                + "\nPlease only use these following commands:\n"
                + "todo\n"
                + "deadline\n"
                + "event\n"
                + "mark\n"
                + "unmark\n"
                + "bye");
    }
}
