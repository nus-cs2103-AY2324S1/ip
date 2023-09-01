package Exceptions;

public class InvalidCommandException extends RunException {
    public InvalidCommandException(String input) {
        super("☹ OOPS!!! A valid command is required. Your current command \"" + input + "\" is not a valid command.\n\n" +
                "Please enter your input starting with :\n" +
                "bye\n" + "list\n" + "todo\n" + "deadline\n" + "event\n" + "mark\n" + "unmark\n" + "delete\n\n" +
                "After the command word, leave a space before typing your task.\n"
        );
    }
}
