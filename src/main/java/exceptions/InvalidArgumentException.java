package exceptions;

public class InvalidArgumentException extends HachiException {
    public InvalidArgumentException(String cmd) {
        super(String.format("Invalid argument for command \"%s\"", cmd));
    }
}