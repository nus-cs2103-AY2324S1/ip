package exceptions.syntax;

public class MissingUnnamedArgsException extends ArgErrorException {
    /**
     * Constructor for an exception, which constructs it with a non-user facing message, a user-facing message and
     * a cause
     *
     * @param message     the message that is NOT meant to be seen by the user, e.g. for debugging
     * @param userMessage the message that IS meant to be seen as user feedback
     * @param cause       what caused this, see java.Exceptions for more details
     */
    public MissingUnnamedArgsException(int numGivenArgs, int numExpectedArgs, Throwable cause) {
        super(String.format("User gave %s args, but expected %s args!",
                        numGivenArgs, numExpectedArgs),
                String.format("You gave me only %s arguments, but I need at %s!",
                        numGivenArgs, numExpectedArgs),
                cause);
    }
}
