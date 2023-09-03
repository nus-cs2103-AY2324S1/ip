package duke;

import java.time.format.DateTimeParseException;

import exceptions.DukeException;
import exceptions.InvalidDeadlineException;
import exceptions.InvalidEventException;
import exceptions.InvalidIndexException;
import exceptions.InvalidTodoException;
import exceptions.UnknownCommandException;


/**
 * Parses user input and instructs Duke to act on the relevant command.
 */
public class Parser {

    /**
     * Parses the user input and asks Duke to invoke the relevant functions.
     *
     * @param userInput The command and argument that the user enters.
     * @param taskListSize The current size of the task list.
     * @return The command of the user input, if valid.
     * @throws UnknownCommandException If the command doesn't match any of Duke's commands.
     * @throws InvalidIndexException If the index provided is out of range, where relevant.
     * @throws InvalidTodoException If the user input is invalid (e.g. no description).
     * @throws InvalidDeadlineException If the user input is invalid (e.g. no deadline).
     * @throws InvalidEventException If the user input is invalid (e.g. no start).
     */
    public static String parseUserInput(String userInput, int taskListSize)
            throws UnknownCommandException,
            InvalidIndexException,
            InvalidTodoException,
            InvalidDeadlineException,
            InvalidEventException {
        String[] inputArr = userInput.trim().split(" ");
        if (inputArr.length == 0) {
            throw new UnknownCommandException();
        }

        String command = inputArr[0];
        switch(command) {
        case "list":
            return Duke.listTasks();
        case "find":
            return parseFilter(userInput);
        case "mark":
            return parseMark(userInput, taskListSize);
        case "unmark":
            return parseUnmark(userInput, taskListSize);
        case "delete":
            return parseDelete(userInput, taskListSize);
        case "todo":
            return parseTodo(userInput);
        case "deadline":
            return parseDeadline(userInput);
        case "event":
            return parseEvent(userInput);
        default:
            throw new UnknownCommandException();
        }
    }

    private static String parseFilter(String userInput)
            throws DukeException {
        String restOfInput = userInput.trim().substring(4).trim();
        if (restOfInput.equals("")) {
            throw new DukeException("No keyword was provided, please enter again.");
        }
        return Duke.listFilteredTasks(restOfInput);
    }

    private static String parseMark(String userInput, int taskListSize)
            throws InvalidIndexException {
        String restOfInput = userInput.trim().substring(4).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput);
            return Duke.markTask(taskNum);
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static String parseUnmark(String userInput, int taskListSize)
            throws InvalidIndexException {
        String restOfInput = userInput.trim().substring(6).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput);
            return Duke.unmarkTask(taskNum);
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static String parseDelete(String userInput, int taskListSize)
            throws InvalidIndexException {
        String restOfInput = userInput.trim().substring(6).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput);
            return Duke.deleteTask(taskNum);
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static String parseTodo(String userInput)
            throws InvalidTodoException {
        String restOfInput = userInput.trim().substring(4).trim();
        if (restOfInput.equals("")) {
            throw new InvalidTodoException();
        }
        return Duke.createTodo(restOfInput);
    }

    private static String parseDeadline(String userInput)
            throws InvalidDeadlineException {
        String restOfInput = userInput.trim().substring(8).trim();
        try {
            if (!restOfInput.contains("/by")) {
                throw new InvalidDeadlineException();
            }

            String[] arr = restOfInput.split("/by");
            if (arr.length < 2) {
                throw new InvalidDeadlineException();
            }

            String desc = arr[0].trim();
            String deadline = arr[1].trim();

            if (desc.equals("") || deadline.equals("")) {
                throw new InvalidDeadlineException();
            }
            return Duke.createDeadline(desc, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    private static String parseEvent(String userInput)
            throws InvalidEventException {
        String restOfInput = userInput.trim().substring(5).trim();
        try {
            if (!restOfInput.contains("/from") || !restOfInput.contains("/to")) {
                throw new InvalidEventException();
            }

            String[] arr = restOfInput.split("/from|/to");
            if (arr.length < 3) {
                throw new InvalidEventException();
            }

            String desc = arr[0].trim();
            String start = arr[1].trim();
            String end = arr[2].trim();

            if (desc.equals("") || start.equals("") || end.equals("")) {
                throw new InvalidEventException();
            }
            return Duke.createEvent(desc, start, end);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }

}
