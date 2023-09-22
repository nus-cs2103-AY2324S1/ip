package atlas.exceptions;

/**
 * Exception for commands that require arguments but none are given
 */
public class MissingCommandArgsException extends RuntimeException {
    private final String commandType;

    /**
     * Constructor for a MissingCommandArgsException
     * @param commandType The name of the command that has missing arguments
     */
    public MissingCommandArgsException(String commandType) {
        this.commandType = commandType;
    }

    @Override
    public String getMessage() {
        return "You speak of \"" + commandType + "\" but you refuse to elaborate. How am I to help?";
    }
}
