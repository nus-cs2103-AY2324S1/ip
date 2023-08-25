package exceptions;

public class EmptyTaskException extends HachiException {
    public EmptyTaskException(String task) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.", task));
    }
}