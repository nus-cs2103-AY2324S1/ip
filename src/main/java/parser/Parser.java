package parser;

import commands.*;
import exception.URChatBotException;
import ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Parser{
    Ui ui = new Ui();
    private enum CommandLine {
        BYE,
        DELETE,
        CLEAR,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        PRINT,
        EVENT
    }

    public static Command parse(String command) throws URChatBotException {
        String commandType = getCommandType(command);
        switch (commandType) {
            case "TODO":
                return parseTodoCommand(command);
            case "DEADLINE":
                return parseDeadlineCommand(command);
            case "EVENT":
                return parseEventCommand(command);
            case "LIST":
                return parseListCommand(command);
            case "MARK":
                return parseMarkCommand(command);
            case "UNMARK":
                return parseUnmarkCommand(command);
            case "CLEAR":
                return parseClearCommand(command);
            case "DELETE":
                return parseDeleteCommand(command);
            case "PRINT":
                return parsePrintCommand(command);
            case "BYE":
                return parseExitCommand(command);
            default:
                throw new URChatBotException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
    public static String changeTimeFormat(String time) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime d = LocalDateTime.parse(time, formatter);
            return d.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
        } catch (DateTimeParseException e){
            try {
                LocalDate d = LocalDate.parse(time);
                return d.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException err) {
                try {
                    LocalTime t = LocalTime.parse(time);
                    return t.format(DateTimeFormatter.ofPattern("HH:mm"));
                } catch (DateTimeParseException tErr) {
                    return null;
                }
            }
        }
    }


    private static String getCommandType(String command) {
        return command.split("\\s")[0].toUpperCase();
    }

    private static Command parseTodoCommand(String command) throws URChatBotException {
        if (command.length() <= 5) {
            throw new URChatBotException("OOPS!!! The description of a todo cannot be empty.");
        }
        String task = command.substring(command.indexOf("todo") + 5);
        return new TodoCommand(command);
    }
    private static Command parseDeadlineCommand(String command) throws URChatBotException {
        if (command.length() <= 5) {
            throw new URChatBotException("OOPS!!! The description of a deadline cannot be empty.");
        }
        if (!command.contains("/by") || command.substring(command.indexOf("/by") + 3).trim().length() < 1) {
            throw new URChatBotException("OOPS!!! The deadline cannot be empty.");
        }
        String task = command.substring(command.indexOf("deadline") + 9, command.indexOf("/by") - 1);
        String by = command.substring(command.indexOf("/by") + 4).trim();
        String time = changeTimeFormat(by);
        if (time == null) {
            throw new URChatBotException("Wrong DateTime format. Please enter 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'");
        }
        return new DeadlineCommand(task, time);
    }
    private static Command parseEventCommand(String command) throws URChatBotException {

        if (command.trim().length() <= 5) {
            throw new URChatBotException("OOPS!!! The description of a event cannot be empty.");
        }
        if (!command.contains("/from") || !command.contains("/to")
                || command.substring(command.indexOf("event") + 6, command.indexOf("/from")).trim().length() < 1
                || command.substring(command.indexOf("/from") + 5, command.indexOf("/to") - 1).trim().length() < 1
                || command.substring(command.indexOf("/to") + 3).trim().length() < 1) {
            throw new URChatBotException("OOPS!!! The task name or/and from or/and to cannot be empty.");
        }
        if (!command.contains("/from") || !command.contains("/to")
                || command.substring(command.indexOf("/from") + 5, command.indexOf("/to") - 1).trim().length() < 1
                || command.substring(command.indexOf("/to") + 3).trim().length() < 1) {
            throw new URChatBotException("OOPS!!! The from or/and to cannot be empty.");
        }

        String task = command.substring(command.indexOf("event") + 6, command.indexOf("/from") - 1);
        String from = command.substring(command.indexOf("/from") + 6, command.indexOf("/to") - 1).trim();
        String to = command.substring(command.indexOf("/to") + 4).trim();
        String timeFrom = changeTimeFormat(from);
        String timeTo = changeTimeFormat(to);
        if (timeFrom == null || timeTo == null) {
            throw new URChatBotException("Wrong DateTime format. Please enter 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'");
        }
        return new EventCommand(task, timeFrom, timeTo);
    }
    private static Command parseMarkCommand(String command) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
        return new MarkCommand(value);
    }

    private static Command parseUnmarkCommand(String command) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
        return new UnmarkCommand(value);
    }
    private static Command parseClearCommand(String command) {
        return new ClearCommand(command);
    }
    private static Command parseListCommand(String command) {
        return new ListCommand(command);
    }
    private static Command parseDeleteCommand(String command) {
        int value = Integer.parseInt(command.replaceAll("[^0-9]", "")) - 1;
        return new DeleteCommand(value);
    }
    private static Command parsePrintCommand(String command) throws URChatBotException {
        String date = command.substring(command.indexOf("print") + 6).trim();
        String formattedDate = changeTimeFormat(date);
        if (formattedDate == null) {
            throw new URChatBotException("Wrong DateTime format. Please enter 'yyyy-MM-dd HH:mm' or 'yyyy-MM-dd'");
        }
        return new PrintCommand(formattedDate);
    }
    private static Command parseExitCommand(String command) {
        return new ExitCommand(command);
    }


    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }
}
