package nobita.parser;

import nobita.command.*;
import nobita.exception.NobitaException;

import java.rmi.NotBoundException;

public class Parser {
    public static Command parse(String fullCommand)  throws NobitaException {
        String[] commands = fullCommand.split(" ", 2);
        String commandType = commands[0].toLowerCase();
        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":

            return new MarkCommand(checkNumber(commands[1]));
        case "unmark":
            return new UnmarkCommand(checkNumber(commands[1]));
        case "todo":
            if (commands.length < 2) {
                throw new NobitaException("The description of a todo cannot be empty.\n"
                        + "Please specify a description.");
            }
            return new AddCommand(commands[1]);
        case "deadline":
            if (commands.length < 2) {
                throw new NobitaException("The description of a deadline cannot be empty.\n"
                        + "Please specify a description.");
            }
            String[] deadlineFields = commands[1].split(" /by ");
            return new AddCommand(deadlineFields[0], deadlineFields[1]);
        case "event":
            if (commands.length < 2) {
                throw new NobitaException("The description of a event cannot be empty.\n"
                        + "Please specify a description.");
            }
            String[] eventFields = commands[1].split(" /from ");
            String[] fromAndTo = eventFields[1].split(" /to ");
            return new AddCommand(eventFields[0], fromAndTo[0], fromAndTo[1]);
        case "delete":
            return new DeleteCommand(Integer.parseInt(commands[1]) - 1);
        default:
            throw new NobitaException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int checkNumber(String toTest) throws NobitaException {
        try {
            return  Integer.parseInt(toTest) - 1;
        } catch (NumberFormatException e) {
            throw new NobitaException("Only numbers are allow");
        }
    }

    private static void checkParameterLength(String[] toTest, int actual) throws NobitaException {
        int len = toTest.length;
        if (len < actual) {
            throw new NobitaException(String.format("Expected %d parameter but only received %d", actual, len));
        }
    }
}
