package chatbot.exceptions;

public abstract class MissingFieldException extends ChatBotException {
    @Override
    public String toString() {
        return "OOPS, your command is valid, but it is missing an important field";
    }
}
