package exceptions;

public class TooManyArgumentsException extends HachiException {
    public TooManyArgumentsException(String cmd, int expected, int actual) {
        super(String.format("☹ OOPS!!! Expected %d arguments for command \"%s\", instead got %d",
                expected, cmd, actual));
    }
}