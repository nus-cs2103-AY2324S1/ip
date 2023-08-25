package exceptions;

public class TaskIndexOutOfBoundsException extends DevyBotException {
    public TaskIndexOutOfBoundsException(int index) {
        super("☹ OOPS!!! The task index " + index + " does not exist.");
    }
}
