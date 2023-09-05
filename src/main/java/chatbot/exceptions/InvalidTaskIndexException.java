package chatbot.exceptions;

public class InvalidTaskIndexException extends ChatBotException {
    @Override
    public String toString() {
        return "\tOOPS, invalid index provided.\n"
                + "\tPlease provide an integer from 1 to the number of tasks in your task list.";
    }
}
