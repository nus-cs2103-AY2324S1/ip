package atlas.exceptions;

/**
 * Exception class for unknown command names
 */
public class UnknownCommandException extends RuntimeException {
    static final String MESSAGE_PREFIX = "Your words are meaningless, mortal. I don't know what is this \"";
    static final String MESSAGE_SUFFIX = "\" that you speak of. Why don't you ask the Olympians for help?";

    private final String errorCommand;

    /**
     * Constructs an Unknown Command Exception.
     * @param errorCommand The command name that does not match an existing command name.
     */
    public UnknownCommandException(String errorCommand) {
        this.errorCommand = errorCommand;
    }

    @Override
    public String getMessage() {
        return MESSAGE_PREFIX + this.errorCommand + MESSAGE_SUFFIX;
    }
}
