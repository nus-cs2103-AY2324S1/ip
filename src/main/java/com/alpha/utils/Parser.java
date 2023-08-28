package com.alpha.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.alpha.commands.ByeCommand;
import com.alpha.commands.Command;
import com.alpha.commands.DeadlineCommand;
import com.alpha.commands.DeleteCommand;
import com.alpha.commands.EventCommand;
import com.alpha.commands.ListCommand;
import com.alpha.commands.MarkCommand;
import com.alpha.commands.ToDoCommand;
import com.alpha.commands.UnmarkCommand;
import com.alpha.exceptions.InvalidTaskException.InvalidDeadlineException;
import com.alpha.exceptions.InvalidTaskException.InvalidEventException;
import com.alpha.exceptions.InvalidTaskException.InvalidToDoException;

public class Parser {

    private Parser() {
    }

    public static String getToDoName(String text) throws InvalidToDoException {
        String result = text.replaceFirst("todo", "").replaceFirst(" ", "");
        if (result.isEmpty()) {
            throw new InvalidToDoException();
        }
        return result;
    }

    public static String getDeadlineName(String text) throws InvalidDeadlineException {
        String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
        int indexOfLastBy = result.lastIndexOf("/by ");
        if (indexOfLastBy == -1) {
            throw new InvalidDeadlineException();
        }
        return result.substring(0, indexOfLastBy - 1);
    }

    public static String getDeadlineEnd(String text) throws InvalidDeadlineException {
        String result = text.replaceFirst("deadline", "").replaceFirst(" ", "");
        int indexOfLastBy = result.lastIndexOf("/by ");
        if (indexOfLastBy == -1 || indexOfLastBy + 4 >= result.length()) {
            throw new InvalidDeadlineException();
        }
        return result.substring(indexOfLastBy + 4);
    }

    public static String getEventName(String text) throws InvalidEventException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastFrom = result.lastIndexOf("/from");
        if (indexOfLastFrom == -1) {
            System.out.println("a");
            throw new InvalidEventException();
        }
        return result.substring(0, indexOfLastFrom);
    }

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

    public static String getEventEnd(String text) throws InvalidEventException {
        String result = text.replaceFirst("event", "").replaceFirst(" ", "");
        int indexOfLastTo = result.lastIndexOf("/to ");
        if (indexOfLastTo == -1 || indexOfLastTo + 4 >= result.length()) {
            System.out.println(result.length());
            throw new InvalidEventException();
        }
        return result.substring(indexOfLastTo + 4);
    }

    public static LocalDateTime parseDateTimeString(String text) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(text, formatter);
    }

    public static String parseDateTimeObjectToDisplay(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm"));
    }

    public static String parseDateTimeObjectToStore(LocalDateTime localDateTime) {
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }

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
        default:
            return null;
        }
    }
}
