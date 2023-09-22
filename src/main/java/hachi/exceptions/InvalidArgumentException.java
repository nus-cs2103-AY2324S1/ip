package hachi.exceptions;

/**
 * Exception thrown when the argument given is invalid for the command.
 */
public class InvalidArgumentException extends HachiException {
    public InvalidArgumentException(String cmd) {
        super(String.format("Invalid argument for command \"%s\"", cmd));
    }
}
