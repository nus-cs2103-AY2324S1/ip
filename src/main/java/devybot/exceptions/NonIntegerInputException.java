package devybot.exceptions;

public class NonIntegerInputException extends DevyBotException {
    public NonIntegerInputException() {
        super("☹ OOPS!!! Please provide a valid task number to mark.");
    }
}
