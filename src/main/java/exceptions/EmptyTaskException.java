package exceptions;

public class EmptyTaskException extends BocchiException {
    public EmptyTaskException(String taskType) {
        super(String.format("☹ OOPS!!! The description of a %s cannot be empty.", taskType));
    }
}
