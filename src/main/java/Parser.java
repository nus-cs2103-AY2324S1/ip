import exceptions.*;
import extensions.Task;
import extensions.TaskList;

import java.time.format.DateTimeParseException;

public class Parser {

    public static void processInput(String userInput, int taskListSize) {
        String[] inputArr = userInput.trim().split(" ");
        if (inputArr.length == 0) {
            throw new UnknownCommandException();
        }

        String command = inputArr[0];
        switch(command) {
        case "list":
            Duke.listTasks();
            break;
        case "mark":
            parseMark(userInput, taskListSize);
            break;
        case "unmark":
            parseUnmark(userInput, taskListSize);
            break;
        case "delete":
            parseDelete(userInput, taskListSize);
            break;
        case "todo":
            parseTodo(userInput, taskListSize);
            break;
        case "deadline":
            parseDeadline(userInput, taskListSize);
            break;
        case "event":
            parseEvent(userInput, taskListSize);
            break;
        default:
            throw new UnknownCommandException();
        }
    }

    private static void parseMark(String userInput, int taskListSize) {
        String restOfInput = userInput.trim().substring(4).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput); // throw NFE
            Duke.mark2(taskNum); // throw DukeEx
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static void parseUnmark(String userInput, int taskListSize) {
        String restOfInput = userInput.trim().substring(6).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput); // throw NFE
            Duke.unmark2(taskNum); // throw DukeEx
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static void parseDelete(String userInput, int taskListSize) {
        String restOfInput = userInput.trim().substring(6).trim();
        try {
            int taskNum = Integer.parseInt(restOfInput); // throw NFE
            Duke.deleteTask2(taskNum); // throw DukeEx
        } catch (NumberFormatException | DukeException e) {
            throw new InvalidIndexException(taskListSize);
        }
    }

    private static void parseTodo(String userInput, int taskListSize) {
        String restOfInput = userInput.trim().substring(4).trim();
        if (restOfInput.equals("")) {
            throw new InvalidTodoException();
        }
        Duke.createTodo2(restOfInput);
    }

    private static void parseDeadline(String userInput, int taskListSize) {
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
            Duke.createDeadline2(desc, deadline);
        } catch (DateTimeParseException e) {
            throw new InvalidDeadlineException();
        }
    }

    private static void parseEvent(String userInput, int taskListSize) {
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
            Duke.createEvent2(desc, start, end);
        } catch (DateTimeParseException e) {
            throw new InvalidEventException();
        }
    }


}
