package Jeoe.Exceptions;

/**
 * This class encapsulates the class InvalidCommandException.
 * It is an exception thrown when an invalid command was inputted by the user.
 *
 * @author Joe Chua
 * @version Week-3
 */
public class InvalidCommandException extends RunException {

    /**
     * Constructor for a InvalidCommandException object.
     * @param input The string input by the user to be included in the error message.
     */
    public InvalidCommandException(String input) {
        super("☹ OOPS!!! A valid command is required. Your current command \"" + input
                + "\" is not a valid command.\n\n"
                + "Please enter your input starting with :\n"
                + "bye\n" + "list\n" + "todo\n" + "deadline\n" + "event\n" + "mark\n" + "unmark\n" + "delete\n\n"
                + "After the command word, leave a space before typing your task.\n"
        );
    }
}
