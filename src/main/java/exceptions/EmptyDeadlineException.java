package exceptions;

public class EmptyDeadlineException extends HachiException {
    public EmptyDeadlineException(String task) {
        super(String.format("☹ OOPS!!! The /by of a %s cannot be empty.", task));
    }
}