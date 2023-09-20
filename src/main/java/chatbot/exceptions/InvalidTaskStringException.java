package chatbot.exceptions;

/**
 * Class that represents an exception due to wrong string representation of a task.
 */
public class InvalidTaskStringException extends ChatBotException {
    @Override
    public String toString() {
        return "Invalid String representation of task";
    }
}
