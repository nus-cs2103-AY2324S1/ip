package chatbot.exceptions;

public class InvalidTaskIndexException extends ChatBotException {
    @Override
    public String toString() {
        return "OOPS, invalid index provided.\n"
                + "Please provide an integer from 1 to the number of tasks in your task list.";
    }
}
