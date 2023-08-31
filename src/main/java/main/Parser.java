package main;

import command.ExitCommand;
import command.UndoCommand;
import exception.DialogixException;
import task.TaskType;
import command.Command;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {
    public static Command parse(String fullCommand) throws DialogixException {
        if (isExitCommand(fullCommand)) {
            return new ExitCommand();
        } else if (isUndoCommand(fullCommand)) {
            String newInput = fullCommand.replaceFirst("undo", "").trim();
            int stepsToUndo = validateUndo(newInput);
            return new UndoCommand(stepsToUndo);
        } else if (isListCommand(fullCommand)) {
            // return new ListCommand();
        } else if (isDoneCommand(fullCommand)) {
            String newInput = fullCommand.replaceFirst("done", "").trim();
            int oneBasedIndex = validateDoneIndex(newInput);
            // return new DoneCommand(oneBasedIndex);
        } else if (isAddTodoCommand(fullCommand)) {
            String todo = fullCommand.replaceFirst("todo", "").trim();
            validateTodo(todo);
            // return new AddTodoCommand(todo);
        } else if (isAddDeadlineCommand(fullCommand)) {
            String[] deadline = validateDeadline(fullCommand);
            if (isDate(deadline[1])) {
                Date deadlineDate = parseDate(deadline[1]);
                // return new AddDeadlineCommand(deadline[0], deadlineDate);
            } else {
                // return new AddDeadlineCommand(deadline[0], deadline[1]);
            }
        } else if (isAddEventCommand(fullCommand)) {
            String[] event = validateEvent(fullCommand);
            if (isDate(event[1])) {
                Date eventDate = parseDate(event[1]);
                // return new AddEventCommand(event[0], eventDate);
            } else {
                // return new AddEventCommand(event[0], event[1]);
            }
        } else if (isDeleteCommand(fullCommand)) {
            String newInput = fullCommand.replaceFirst("delete", "").trim();
            int oneBasedIndex = validateDeleteIndex(newInput);
            // return new DeleteCommand(oneBasedIndex);
        } else if (isFindCommand(fullCommand)) {
            String toFind = fullCommand.replaceFirst("find", "").trim();
            validateFindInput(toFind);
            // return new FindCommand(toFind);
        } else {
            // throw new DialogixException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return null;
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
        // Assume date is in the format 2/12/2019 1800
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
        String[] splitInput = input.replaceFirst(textToReplace, "")
            .trim().split(textToSplit);

        for (int i = 0; i < splitInput.length; i++) {
            splitInput[i] = splitInput[i].trim();
        }

        if (splitInput.length != 2 || splitInput[0].isBlank() || splitInput[1].isBlank()) {
            throw new DialogixException("OOPS!!! I had trouble processing that input.\n"
                + "Please make sure that the task description and dates are not empty!");
        }
        return splitInput;
    }

    private static boolean isUndoCommand(String input) {
        return input.startsWith("undo");
    }

    private static boolean isFindCommand(String input) {
        return input.startsWith("find");
    }

    private static boolean isExitCommand(String input) {
        return "bye".equals(input);
    }

    private static boolean isListCommand(String input) {
        return "list".equals(input);
    }

    private static boolean isDoneCommand(String input) {
        return input.startsWith("done");
    }

    private static boolean isAddTodoCommand(String input) {
        return input.startsWith("todo");
    }

    private static boolean isAddDeadlineCommand(String input) {
        return input.startsWith("deadline");
    }

    private static boolean isAddEventCommand(String input) {
        return input.startsWith("event");
    }

    private static boolean isDeleteCommand(String input) {
        return input.startsWith("delete");
    }
}
