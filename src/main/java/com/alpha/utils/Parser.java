package com.alpha.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.alpha.commands.ByeCommand;
import com.alpha.commands.Command;
import com.alpha.commands.DeadlineCommand;
import com.alpha.commands.DeleteCommand;
import com.alpha.commands.EventCommand;
import com.alpha.commands.FindCommand;
import com.alpha.commands.ListCommand;
import com.alpha.commands.MarkCommand;
import com.alpha.commands.ToDoCommand;
import com.alpha.commands.UnmarkCommand;
import com.alpha.exceptions.InvalidTaskException.InvalidDeadlineException;
import com.alpha.exceptions.InvalidTaskException.InvalidEventException;
import com.alpha.exceptions.InvalidTaskException.InvalidToDoException;

/**
 * The type Parser.
 */
public class Parser {

    private Parser() {
    }

    /**
     * Gets search string from user input.
     *
     * @param text User input.
     * @return Search string of the task.
     */
    public static String getFindSearchString(String text) {
        String result = text.replaceFirst("find", "").replaceFirst(" ", "");
        return result;
    }

    /**
     * Gets to do task name from user input.
     *
     * @param text User input.
     * @return Name of the todo task.
     * @throws InvalidToDoException If the input is invalid.
     */
    public static String getToDoName(String text) throws InvalidToDoException {
        String result = text.replaceFirst("todo", "").replaceFirst(" ", "");
        if (result.isEmpty()) {
            throw new InvalidToDoException();
        }
        return result;
    }

    /**
     * Gets deadline task name from user input.
     *
     * @param text User input.
     * @return Name of the deadline task.
     * @throws InvalidDeadlineException If the input is invalid.
     */
    public static String getDeadlineName(String text) throws InvalidDeadlineException {
        String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
        int indexOfLastBy = result.lastIndexOf("/by ");
        if (indexOfLastBy == -1) {
            throw new InvalidDeadlineException();
        }
        return result.substring(0, indexOfLastBy - 1);
    }

    /**
     * Gets deadline end datetime from user input.
     *
     * @param text User input.
     * @return Deadline end datetime.
     * @throws InvalidDeadlineException If the input is invalid.
     */
    public static String getDeadlineEnd(String text) throws InvalidDeadlineException {
        String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
        int indexOfLastBy = result.lastIndexOf("/by ");
        if (indexOfLastBy == -1 || indexOfLastBy + 4 >= result.length()) {
            throw new InvalidDeadlineException();
        }
        return result.substring(indexOfLastBy + 4);
    }

    /**
     * Gets event task name from user input.
     *
     * @param text User input.
     * @return Name of the event task.
     * @throws InvalidEventException If the input is invalid.
     */
    public static String getEventName(String text) throws InvalidEventException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastFrom = result.lastIndexOf("/from");
        if (indexOfLastFrom == -1) {
            System.out.println("a");
            throw new InvalidEventException();
        }
        return result.substring(0, indexOfLastFrom);
    }

    /**
     * Gets event start datetime from user input.
     *
     * @param text User input.
     * @return Event start datetime.
     * @throws InvalidEventException If the input is invalid.
     */
    public static String getEventStart(String text) throws InvalidEventException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastFrom = result.lastIndexOf("/from");
        int indexOfLastTo = result.lastIndexOf("/to");
        if (indexOfLastFrom == -1 || indexOfLastTo == -1 || indexOfLastFrom + 6 >= indexOfLastTo) {
            System.out.println("b");
            throw new InvalidEventException();
        }
        return result.substring(indexOfLastFrom + 6, indexOfLastTo - 1);
    }

    /**
     * Gets event end datetime from user input.
     *
     * @param text User input.
     * @return Event end datetime.
     * @throws InvalidEventException If the input is invalid.
     */
    public static String getEventEnd(String text) throws InvalidEventException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastTo = result.lastIndexOf("/to ");
        if (indexOfLastTo == -1 || indexOfLastTo + 4 >= result.length()) {
            System.out.println(result.length());
            throw new InvalidEventException();
        }
        return result.substring(indexOfLastTo + 4);
    }

    /**
     * Parse date time string local date time into LocalDateTime object.
     *
     * @param text User input.
     * @return Parsed LocalDateTime object.
     */
    public static LocalDateTime parseDateTimeString(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(text, formatter);
    }

    /**
     * Parse LocalDateTime object to display string.
     *
     * @param localDateTime LocalDateTime object.
     * @return Parsed datetime string to be displayed.
     */
    public static String parseDateTimeObjectToDisplay(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    /**
     * Parse LocalDateTime object to store string.
     *
     * @param localDateTime LocalDateTime object.
     * @return Parsed datetime string to be stored.
     */
    public static String parseDateTimeObjectToStore(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

    /**
     * Parse command from user input.
     *
     * @param userInput User input
     * @return Parsed command.
     */
    public static Command parse(String userInput) {
        String[] tokens = userInput.split(" ");

        switch (tokens[0]) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(tokens[1]);
        case "unmark":
            return new UnmarkCommand(tokens[1]);
        case "delete":
            return new DeleteCommand(tokens[1]);
        case "bye":
            return new ByeCommand();
        case "todo":
            return new ToDoCommand(userInput);
        case "deadline":
            return new DeadlineCommand(userInput);
        case "event":
            return new EventCommand(userInput);
        case "find":
            return new FindCommand(userInput);
        default:
            return null;
        }
    }
}
