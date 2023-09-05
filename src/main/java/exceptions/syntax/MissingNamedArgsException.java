package exceptions.syntax;

import java.util.List;

/**
 * Exception thrown when an expected named argument is missing
 */
public class MissingNamedArgsException extends ArgErrorException {

    /**
     * Constructor for this exception
     *
     * @param expectedArgs     the message that is NOT meant to be seen by the user, e.g. for debugging
     * @param providedArgs the message that IS meant to be seen as user feedback
     * @param cause       what caused this, see java.Exceptions for more details
     */
    public MissingNamedArgsException(List<? extends String> expectedArgs, List<? extends String> providedArgs,
                                     Throwable cause) {
        super(String.format("Expected all of %s args, but only got %s",
                        expectedArgs.toString(), providedArgs.toString()),
                String.format("I need all of %s as arguments, but you gave me only %s!",
                        expectedArgs.toString(), providedArgs.toString()),
                cause);
    }
}
