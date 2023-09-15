package duke.exception;

/** Duke NumberFormatException. Is thrown when a non number argument is given when required. */
public class ArgumentMustBeNumException extends DukeException {
    /**
     * Returns an ArgumentMustBeNumException
     *
     * @param commandName the name of the command
     * @return an ArgumentMustBeNumException
     */
    public ArgumentMustBeNumException(String commandName) {
        super(String.format("The argument of %s must be a number.", commandName));
    }
}
