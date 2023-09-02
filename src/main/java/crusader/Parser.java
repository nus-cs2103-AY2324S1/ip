package crusader;

import crusader.command.*;
import crusader.exception.CrusaderDateFormatException;
import crusader.exception.CrusaderException;
import crusader.exception.CrusaderParseException;
import crusader.task.Deadline;
import crusader.task.Event;
import crusader.task.Todo;

import java.text.ParseException;

public class Parser {
    public static Command parse(String prompt) throws CrusaderException {
        Command returnCommand;
        switch (prompt.contains(" ")
                ? prompt.split(" ")[0]
                : prompt) {
            case "bye":
                returnCommand = new ByeCommand();
                break;
            case "list":
                returnCommand = new ListCommand();
                break;
            case "mark":
                returnCommand = parseMarking(prompt, true);
                break;
            case "unmark":
                returnCommand = parseMarking(prompt, false);
                break;
            case "todo":
                returnCommand = parseTodo(prompt);
                break;
            case "event":
                returnCommand = parseEvent(prompt);
                break;
            case "deadline":
                returnCommand = parseDeadline(prompt);
                break;
            case "delete":
                returnCommand = parseDelete(prompt);
                break;
            default:
                throw new CrusaderParseException("Unknown command!");
        }
        return returnCommand;
    }

    private static Command parseMarking(String currentPrompt, boolean isMarkCommand) throws CrusaderException {
        String[] components = currentPrompt.split(" ");
        if (components.length != 2) {
            throw new CrusaderParseException("un/mark expects 1 parameter!");
        }
        try {
            int i = Integer.parseInt(currentPrompt.split(" ")[1]);
            return new MarkCommand(i, isMarkCommand);
        } catch (NumberFormatException e) {
            throw new CrusaderParseException("un/mark expects its 1 parameter to be a number!");
        }
    }

    private static Command parseTodo(String prompt) throws CrusaderException {
        if (prompt.trim().length() < 5) {
            throw new CrusaderParseException("todo expects 1 parameter!");
        }
        String name = prompt.substring(5).trim();
        Todo t = new Todo(name);
        return new AddTaskCommand(t);
    }

    private static Command parseEvent(String prompt) throws CrusaderParseException {
        int fromPosition = prompt.indexOf("/from");
        if (fromPosition < 0) {
            throw new CrusaderParseException("An event must have a /from parameter!");
        }
        int toPosition = prompt.indexOf("/to");
        if (toPosition < 0) {
            throw new CrusaderParseException("An event must have /to parameter!");
        }
        if (toPosition <= fromPosition ) {
            throw new CrusaderParseException("/to should be in front of /from!");
        }
        if (fromPosition < 7) {
            throw new CrusaderParseException("There should be an event name!");
        }
        if (fromPosition + 6 > toPosition - 1) {
            throw new CrusaderParseException("Please specify a /from parameter!");
        }
        if (toPosition + 4 > prompt.trim().length()) {
            throw new CrusaderParseException("Please specify a /to parameter!");
        }
        String name = prompt.substring(6, fromPosition - 1).trim();
        String from = prompt.substring(fromPosition + 6, toPosition - 1).trim();
        String to = prompt.substring(toPosition + 4).trim();
        try {
            Event e = new Event(name, DateUtils.parseDateTime(from), DateUtils.parseDateTime(to));
            return new AddTaskCommand(e);
        } catch (ParseException e) {
            throw new CrusaderDateFormatException();
        }
    }

    private static Command parseDeadline(String prompt) throws CrusaderParseException {
        int byPosition = prompt.indexOf("/by");
        if (byPosition < 0) {
            throw new CrusaderParseException("A deadline must have a /by parameter!");
        }
        if (byPosition + 4 > prompt.trim().length()) {
            throw new CrusaderParseException("Please specify a /by parameter!");
        }
        String name = prompt.substring(9, byPosition - 1).trim();
        String by = prompt.substring(byPosition + 4).trim();
        try {
            Deadline d = new Deadline(name, DateUtils.parseDateTime(by));
            return new AddTaskCommand(d);
        } catch (ParseException e) {
            throw new CrusaderDateFormatException();
        }
    }

    private static Command parseDelete(String prompt) throws CrusaderParseException {
        String[] components = prompt.split(" ");
        if (components.length != 2) {
            throw new CrusaderParseException("delete expects 1 parameter!");
        }
        try {
            int i = Integer.parseInt(prompt.split(" ")[1]);
            return new DeleteCommand(i);
        } catch (NumberFormatException e) {
            throw new CrusaderParseException("delete expects its 1 parameter to be a number!");
        }
    }
}
