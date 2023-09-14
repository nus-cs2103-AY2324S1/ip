package dialogix.main;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dialogix.command.AddDeadlineCommand;
import dialogix.command.AddEventCommand;
import dialogix.command.AddTodoCommand;
import dialogix.command.Command;
import dialogix.command.DeleteCommand;
import dialogix.command.DoneCommand;
import dialogix.command.ExitCommand;
import dialogix.command.FindCommand;
import dialogix.command.ListCommand;
import dialogix.command.UndoCommand;
import dialogix.exception.DialogixException;

class Parser {
    static Command parse(String fullCommand) throws DialogixException {
        String[] commandParts = fullCommand.split(" ", 2);
        String commandType = commandParts[0].toLowerCase();
        String commandArgument = commandParts.length > 1 ? commandParts[1] : "";

        switch (commandType) {
        case "bye":
            return createExitCommand();
        case "undo":
            return createUndoCommand(commandArgument);
        case "list":
            return createListCommand();
        case "done":
            return createDoneCommand(commandArgument);
        case "todo":
            return createAddTodoCommand(commandArgument);
        case "deadline":
            return createAddDeadlineCommand(commandArgument);
        case "event":
            return createAddEventCommand(commandArgument);
        case "delete":
            return createDeleteCommand(commandArgument);
        case "find":
            return createFindCommand(commandArgument);
        default:
            throw new DialogixException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static ExitCommand createExitCommand() {
        return new ExitCommand();
    }

    private static UndoCommand createUndoCommand(String argument) throws DialogixException {
        int stepsToUndo = validateUndo(argument);
        return new UndoCommand(stepsToUndo);
    }

    private static ListCommand createListCommand() {
        return new ListCommand();
    }

    private static DoneCommand createDoneCommand(String argument) throws DialogixException {
        int oneBasedIndex = validateDoneIndex(argument);
        return new DoneCommand(oneBasedIndex);
    }

    private static AddTodoCommand createAddTodoCommand(String argument) throws DialogixException {
        validateTodo(argument);
        return new AddTodoCommand(argument);
    }

    private static AddDeadlineCommand createAddDeadlineCommand(String argument) throws DialogixException {
        String[] deadlineParts = validateDeadline(argument);
        if (isDate(deadlineParts[1])) {
            Date deadlineDate = parseDate(deadlineParts[1]);
            return new AddDeadlineCommand(deadlineParts[0], deadlineDate);
        } else {
            return new AddDeadlineCommand(deadlineParts[0], deadlineParts[1]);
        }
    }

    private static AddEventCommand createAddEventCommand(String argument) throws DialogixException {
        String[] eventParts = validateEvent(argument);
        if (isDate(eventParts[1])) {
            Date eventDate = parseDate(eventParts[1]);
            return new AddEventCommand(eventParts[0], eventDate);
        } else {
            return new AddEventCommand(eventParts[0], eventParts[1]);
        }
    }

    private static DeleteCommand createDeleteCommand(String argument) throws DialogixException {
        int deleteIndex = validateDeleteIndex(argument);
        return new DeleteCommand(deleteIndex);
    }

    private static FindCommand createFindCommand(String argument) throws DialogixException {
        validateFindInput(argument);
        return new FindCommand(argument);
    }

    static Date parseDate(String date) throws DialogixException {
        try {
            SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HHmm");
            return dateFormatter.parse(date);
        } catch (ParseException e) {
            throw new DialogixException("An error occurred while parsing date: " + e);
        }
    }

    static boolean isDate(String input) {
        String[] splitInput = input.split("/");
        if (splitInput.length != 3 || isNotNumeric(splitInput[0]) || isNotNumeric(splitInput[1])) {
            return false;
        }
        String[] yearAndTime = splitInput[2].split(" ");
        return yearAndTime.length == 2 && !isNotNumeric(yearAndTime[0]) && !isNotNumeric(yearAndTime[1]);
    }

    private static int validateDoneIndex(String doneInput) throws DialogixException {
        return validateDoneOrDeleteIndex(doneInput);
    }

    private static int validateDeleteIndex(String deleteInput) throws DialogixException {
        return validateDoneOrDeleteIndex(deleteInput);
    }

    private static int validateDoneOrDeleteIndex(String doneInput) throws DialogixException {
        if (doneInput.isEmpty() || isNotNumeric(doneInput)) {
            throw new DialogixException("OOPS!!! The index to remove cannot be blank or not an integer.");
        }
        return Integer.parseInt(doneInput);
    }

    private static int validateUndo(String undoInput) throws DialogixException {
        if (undoInput.isEmpty() || isNotNumeric(undoInput)) {
            throw new DialogixException("OOPS!!! The number of steps to undo cannot be blank or not an integer.");
        }
        return Integer.parseInt(undoInput);
    }

    private static boolean isNotNumeric(String input) {
        return !input.matches("-?\\d+(\\.\\d+)?");
    }

    private static void validateTodo(String todo) throws DialogixException {
        if (todo.isEmpty()) {
            throw new DialogixException("OOPS!!! The description of a todo cannot be empty.");
        }
    }

    private static void validateFindInput(String toFind) throws DialogixException {
        if (toFind.isEmpty()) {
            throw new DialogixException("OOPS!!! The provided filter for find cannot be empty.");
        }
    }

    private static String[] validateEvent(String input) throws DialogixException {
        return validateEventOrDeadline(input, "event", "/at");
    }

    private static String[] validateDeadline(String input) throws DialogixException {
        return validateEventOrDeadline(input, "deadline", "/by");
    }

    private static String[] validateEventOrDeadline(String input, String textToReplace, String textToSplit)
            throws DialogixException {
        String[] splitInput = input.replaceFirst(textToReplace, "").trim().split(textToSplit);
        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }
        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DialogixException("OOPS!!! I had trouble processing that input.\n"
                    + "Please make sure that the task description and dates are not empty!");
        }
        return splitInput;
    }
}
