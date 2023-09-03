package Jeoe.Exceptions;

/**
 * This class encapsulates the class NoCommandException.
 * It is an exception thrown when no command was inputted by the user.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class NoCommandException extends RunException {

    /**
     * Constructor for a NoCommandException object.
     * @param input The string input by the user to be included in the error message.
     */
    public NoCommandException(String input) {
        super("☹ OOPS!!! A valid command is required. Your current command \"" + input + "\" is empty.\n\n"
                + "Please enter your input starting with :\n"
                + "bye\n" + "list\n" + "todo\n" + "deadline\n" + "event\n" + "mark\n" + "unmark\n" + "delete\n\n"
                + "After the command word, leave a space before typing your task.\n"
        );
    }
}
