package hachi.exceptions;

/**
 * Exception thrown when too many arguments are supplied to the command
 */
public class TooManyArgumentsException extends HachiException {
    /**
     * Constructor for the TooManyArgumentsException class
     * @param cmd The command
     * @param expected The expected number of arguments for the command.
     * @param actual The actual number of arguments supplied to the command.
     */
    public TooManyArgumentsException(String cmd, int expected, int actual) {
        super(String.format("☹ OOPS!!! Expected %d arguments for command \"%s\", instead got %d",
                expected, cmd, actual));
    }
}
