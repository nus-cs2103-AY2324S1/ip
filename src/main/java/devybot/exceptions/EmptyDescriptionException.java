package devybot.exceptions;

public class EmptyDescriptionException extends DevyBotException {
    public EmptyDescriptionException(String taskType) {
        super("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
    }
}