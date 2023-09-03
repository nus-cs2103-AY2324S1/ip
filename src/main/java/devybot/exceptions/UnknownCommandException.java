package devybot.exceptions;

public class UnknownCommandException extends DevyBotException {
    public UnknownCommandException() {
        super("☹ OOPS!!! I'm sorry, but I don't know what that means :-(. Please enter a valid command");
    }
}