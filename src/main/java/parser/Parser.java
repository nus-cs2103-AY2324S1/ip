package main.java.parser;

import main.java.command.*;
import main.java.exception.EmptyChoiceException;
import main.java.exception.EmptyTodoException;
import main.java.exception.UnknownCommandException;

public class Parser {

    public Command parseInstruction(String input) throws UnknownCommandException, EmptyTodoException, EmptyChoiceException {
        String[] stringArray = input.split(" ", 2);
        switch (stringArray[0]) {
        case "list":
            return new ListCommand();
        case "unmark":
            if (stringArray.length == 1) {
                throw new EmptyChoiceException("☹ OOPS!!! Select the task number to be unmarked.\n");
            }
            return new UnmarkCommand(Integer.parseInt(stringArray[1]) - 1);
        case "mark":
            if (stringArray.length == 1) {
                throw new EmptyChoiceException("☹ OOPS!!! Select the task number to be marked.\n");
            }
            return new MarkCommand(Integer.parseInt(stringArray[1]) - 1);
        case "todo":
            if (stringArray.length == 1) {
                throw new EmptyTodoException("☹ OOPS!!! The description of a todo cannot be empty.\n");
            }
            return new TodoCommand(stringArray[1]);
        case "deadline":
            String[] splitDeadline = stringArray[1].split(" /by ", 2);
            return new DeadlineCommand(splitDeadline[0], splitDeadline[1]);
        case "event":
            String[] splitFrom = stringArray[1].split(" /from ", 2);
            String description = splitFrom[0];
            String[] splitTo = splitFrom[1].split(" /to ", 2);
            String from = splitTo[0];
            String to = splitTo[1];
            return new EventCommand(description, from, to);
        case "delete":
            return new DeleteCommand(Integer.parseInt(stringArray[1]) - 1);
        default:
            throw new UnknownCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }
}
