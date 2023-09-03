package devybot.exceptions;

public class TaskIndexOutOfBoundsException extends DevyBotException {
    public TaskIndexOutOfBoundsException(int index) {
        super("☹ OOPS!!! The task index " + (index + 1) + " does not exist.");
    }
}
