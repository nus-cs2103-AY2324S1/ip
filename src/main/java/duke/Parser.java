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
            Duke.listTasks();
            return "list";
        case "find":
            parseFilter(userInput);
            return "find";
        case "mark":
            parseMark(userInput, taskListSize);
            return "mark";
        case "unmark":
            parseUnmark(userInput, taskListSize);
            return "unmark";
        case "delete":
            parseDelete(userInput, taskListSize);
            return "delete";
        case "todo":
            parseTodo(userInput);
            return "todo";
        case "deadline":
            parseDeadline(userInput);
            return "deadline";
        case "event":
            parseEvent(userInput);
            return "event";
        default:
            throw new UnknownCommandException();
        }
    }

    private static void parseFilter(String userInput)
            throws DukeException {
        String restOfInput = userInput.trim().substring(4).trim();
        if (restOfInput.equals("")) {
            throw new DukeException("No keyword was provided, please enter again.");
        }
        Duke.listFilteredTasks(restOfInput);
    }

    private static void parseMark(String userInput, int taskListSize)
            throws InvalidIndexException {
        String restOfInput = userInput.trim().substring(4).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput);
            Duke.mark(taskNum);
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static void parseUnmark(String userInput, int taskListSize)
            throws InvalidIndexException {
        String restOfInput = userInput.trim().substring(6).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput);
            Duke.unmark(taskNum);
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static void parseDelete(String userInput, int taskListSize)
            throws InvalidIndexException {
        String restOfInput = userInput.trim().substring(6).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput);
            Duke.deleteTask(taskNum);
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static void parseTodo(String userInput)
            throws InvalidTodoException {
        String restOfInput = userInput.trim().substring(4).trim();
        if (restOfInput.equals("")) {
            throw new InvalidTodoException();
        }
        Duke.createTodo(restOfInput);
    }

    private static void parseDeadline(String userInput)
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
            Duke.createDeadline(desc, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    private static void parseEvent(String userInput)
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
            Duke.createEvent(desc, start, end);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }

}
