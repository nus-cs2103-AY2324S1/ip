package com.alpha.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import com.alpha.commands.ByeCommand;
import com.alpha.commands.Command;
import com.alpha.commands.DeadlineCommand;
import com.alpha.commands.DeleteCommand;
import com.alpha.commands.EventCommand;
import com.alpha.commands.FindCommand;
import com.alpha.commands.InvalidCommand;
import com.alpha.commands.ListCommand;
import com.alpha.commands.MarkCommand;
import com.alpha.commands.ToDoCommand;
import com.alpha.commands.UnmarkCommand;
import com.alpha.exceptions.InvalidDateTimeException;
import com.alpha.exceptions.InvalidDateTimeException.InvalidDateTimeFormatException;
import com.alpha.exceptions.InvalidTaskException;
import com.alpha.exceptions.InvalidTaskException.InvalidDeadlineException;
import com.alpha.exceptions.InvalidTaskException.InvalidEventException;
import com.alpha.exceptions.InvalidTaskException.InvalidToDoException;
import com.alpha.tasks.TaskList;

/**
 * The Task parser utility class.
 */
public class TaskParser {

    private TaskParser() {
    }

    private static String getFindSearchString(String text) {
        return text.replaceFirst("find", "").replaceFirst(" ", "");
    }

    private static String getToDoName(String text) throws InvalidToDoException {
        String result = text.replaceFirst("todo", "").replaceFirst(" ", "");
        if (result.isEmpty()) {
            throw new InvalidToDoException("Unable to find todo name.");
        }
        return result;
    }

    private static String getDeadlineName(String text) throws InvalidDeadlineException {
        String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
        int indexOfLastBy = result.lastIndexOf("/by ");
        if (indexOfLastBy == -1) {
            throw new InvalidDeadlineException("Unable to find deadline name.");
        }
        return result.substring(0, indexOfLastBy - 1);
    }

    private static LocalDateTime getDeadlineEnd(String text)
            throws InvalidDeadlineException, InvalidDateTimeException {
        String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
        int indexOfLastBy = result.lastIndexOf("/by ");
        if (indexOfLastBy == -1 || indexOfLastBy + 4 >= result.length()) {
            throw new InvalidDeadlineException("Unable to find deadline end date.");
        }
        try {
            return DateTimeParser.parseDateTimeString(result.substring(indexOfLastBy + 4));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    private static String getEventName(String text) throws InvalidEventException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastFrom = result.lastIndexOf("/from");
        if (indexOfLastFrom == -1) {
            throw new InvalidEventException("Unable to find event name.");
        }
        return result.substring(0, indexOfLastFrom);
    }

    private static LocalDateTime getEventStart(String text)
            throws InvalidEventException, InvalidDateTimeFormatException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastFrom = result.lastIndexOf("/from");
        int indexOfLastTo = result.lastIndexOf("/to");
        if (indexOfLastFrom == -1 || indexOfLastTo == -1 || indexOfLastFrom + 6 >= indexOfLastTo) {
            throw new InvalidEventException("Unable to find event start date.");
        }
        try {
            return DateTimeParser.parseDateTimeString(result.substring(indexOfLastFrom + 6, indexOfLastTo - 1));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    private static LocalDateTime getEventEnd(String text)
            throws InvalidEventException, InvalidDateTimeFormatException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastTo = result.lastIndexOf("/to ");
        if (indexOfLastTo == -1 || indexOfLastTo + 4 >= result.length()) {
            throw new InvalidEventException("Unable to find event end date.");
        }
        try {
            return DateTimeParser.parseDateTimeString(result.substring(indexOfLastTo + 4));
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }

    /**
     * Parse command from the user input.
     *
     * @param userInput The user input.
     * @param taskList  The task list.
     * @return The command.
     * @throws InvalidTaskException     The invalid task exception.
     * @throws InvalidDateTimeException The invalid date time exception.
     */
    public static Command parse(String userInput, TaskList taskList)
            throws InvalidTaskException, InvalidDateTimeException {

        String[] tokens = userInput.split(" ");

        switch (tokens[0]) {
        case "list":
            return new ListCommand(taskList);
        case "mark":
            return new MarkCommand(Integer.parseInt(tokens[1]), taskList);
        case "unmark":
            return new UnmarkCommand(Integer.parseInt(tokens[1]), taskList);
        case "delete":
            return new DeleteCommand(Integer.parseInt(tokens[1]), taskList);
        case "bye":
            return new ByeCommand();
        case "todo":
            return new ToDoCommand(getToDoName(userInput), taskList);
        case "deadline":
            return new DeadlineCommand(getDeadlineName(userInput), getDeadlineEnd(userInput), taskList);
        case "event":
            return new EventCommand(getEventName(userInput), getEventStart(userInput), getEventEnd(userInput),
                    taskList);
        case "find":
            return new FindCommand(getFindSearchString(userInput), taskList);
        default:
            return new InvalidCommand();
        }
    }
}
