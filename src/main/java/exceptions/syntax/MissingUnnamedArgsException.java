package exceptions.syntax;

/**
 * Exception to be thrown when a CommandHandler is missing unnamed arguments.
 */
public class MissingUnnamedArgsException extends ArgErrorException {

    /**
     * Constructor for the exception.
     *
     * @param numGivenArgs     How many arguments were actually provided
     * @param numExpectedArgs How many arguments were expected
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
