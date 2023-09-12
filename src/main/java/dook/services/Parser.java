package dook.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;

import dook.DookException;
import dook.command.*;
import dook.task.Deadline;
import dook.task.Event;
import dook.task.Task;
import dook.task.Todo;

/**
 * Responsible for parsing plaintext commands and returning appropriate
 * Command objects.
 */
public class Parser {
    private static final String MARK_GUIDE_MSG = "Usage: unmark/mark [task number]";
    private static final String TODO_GUIDE_MSG = "Usage: todo [name]";
    private static final String DEADLINE_GUIDE_MSG = "Usage: deadline [name] /by [time].";
    private static final String EVENT_GUIDE_MSG = "Usage: event [name] /from [start] /to [end].";
    private static final String DELETE_GUIDE_MSG = "Usage: delete [task number]";
    private static final String DATE_FORMAT_ERROR_MSG = "Improper Date Format";
    private static final String FIND_GUIDE_MSG = "Usage: find [query]";
    private static final String ALIAS_GUIDE_MSG = "Usage: alias [keyword] [command name]";

    private HashMap<String, CommandInfo> aliasMap = new HashMap<>();

    public void addMapping(String string) {
        String[] str = string.split("//");
        String alias = str[0].trim();
        CommandInfo cmd = CommandInfo.valueOf(str[1].trim());
        aliasMap.put(alias, cmd);
    }

    public void addMapping(String str, CommandInfo cmd) {
        aliasMap.put(str, cmd);
    }

    public void initialise() {
        for (CommandInfo c : CommandInfo.values()) {
            aliasMap.put(c.getName(), c);
        }
    }

    public String getAliasesAsSaveableString() {
        final StringBuilder result = new StringBuilder();
        aliasMap.forEach((str, cmd) -> {
            result.append(str + "//" + cmd.getName() + "\n");
        });
        return result.toString();
    }

    private CommandInfo parseKeyword(String keyword) {
        CommandInfo result = aliasMap.get(keyword);
        if (result != null) {
            return result;
        }
        return CommandInfo.invalid;
    }

    /**
     * Takes a string input and parses it, generating the correct Command
     * object type with the relevant data from user input.
     *
     * @param input The raw string input from the user.
     * @return Command object parsed out from the input.
     * @throws DookException Exception thrown by Dook.
     */
    public Command parseFullInput(String input) throws DookException {
        String[] tmp = input.split(" ", 2);
        CommandInfo command = parseKeyword(tmp[0].trim());
        assert command != null : "command should not be null.";
        String body = tmp.length == 1 ? "" : tmp[1].trim();
        switch (command) {
        case bye:
            return new ByeCommand();
        case list:
            return new ListCommand();
        case save:
            return new SaveCommand();
        case mark:
            return handleMark(body, true);
        case unmark:
            return handleMark(body, false);
        case todo:
            return handleToDo(body);
        case deadline:
            return handleDeadline(body);
        case event:
            return handleEvent(body);
        case delete:
            return handleDelete(body);
        case after:
            return handleAfter(body);
        case before:
            return handleBefore(body);
        case during:
            return handleDuring(body);
        case find:
            return handleFind(body);
        case alias:
            return handleAlias(body);
        default:
            return new InvalidCommand();
        }
    }
    private Command handleMark(String body, boolean value) throws DookException {
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0].trim());
        } catch (NumberFormatException e) {
            throw new DookException(MARK_GUIDE_MSG);
        }
        return value
                ? new MarkCommand(index)
                : new UnmarkCommand(index);
    }
    private Command handleToDo(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException(TODO_GUIDE_MSG);
        }
        Task task = new Todo(body.trim(), false);
        return new AddTaskCommand(task);
    }
    private Command handleDeadline(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException(DEADLINE_GUIDE_MSG);
        }

        String[] tmp = body.split("/by", 2);
        if (tmp.length <= 1) {
            throw new DookException(DEADLINE_GUIDE_MSG);
        }

        String desc = tmp[0].trim();
        String by = tmp[1].trim();
        if (desc.isBlank() || by.isBlank()) {
            throw new DookException("Some information is missing!\n"
                    + DEADLINE_GUIDE_MSG);
        }
        Task task = new Deadline(desc, by, false);
        return new AddTaskCommand(task);
    }
    private Command handleEvent(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException(EVENT_GUIDE_MSG);
        }
        String[] tmp1 = body.split("/from", 2);
        if (tmp1.length <= 1) {
            throw new DookException(EVENT_GUIDE_MSG);
        }

        String desc = tmp1[0].trim();

        String[] tmp2 = tmp1[1].split("/to", 2);
        if (tmp2.length <= 1) {
            throw new DookException(EVENT_GUIDE_MSG);
        }
        String from = tmp2[0].trim();
        String to = tmp2[1].trim();
        if (desc.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DookException("Some information is missing!\n"
                    + EVENT_GUIDE_MSG);
        }

        Task task = new Event(desc, from, to, false);
        return new AddTaskCommand(task);
    }
    private Command handleDelete(String body) throws DookException {
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            throw new DookException(DELETE_GUIDE_MSG);
        }
        return new DeleteTaskCommand(index);
    }
    private Command handleBefore(String body) throws DookException {
        LocalDate localDate;
        try {
            String dateString = body.split(" ", 2)[0].trim();
            localDate = TimeProcessor.getLocalDateFromString(dateString);
        } catch (DateTimeParseException e) {
            throw new DookException(DATE_FORMAT_ERROR_MSG);
        }
        return new BeforeCommand(localDate);
    }

    private Command handleAfter(String body) throws DookException {
        LocalDate localDate;
        try {
            String dateString = body.split(" ", 2)[0].trim();
            localDate = TimeProcessor.getLocalDateFromString(dateString);
        } catch (DateTimeParseException e) {
            throw new DookException(DATE_FORMAT_ERROR_MSG);
        }
        return new AfterCommand(localDate);
    }

    private Command handleDuring(String body) throws DookException {
        LocalDate localDate;
        try {
            String dateString = body.split(" ", 2)[0].trim();
            localDate = TimeProcessor.getLocalDateFromString(dateString);
        } catch (DateTimeParseException e) {
            throw new DookException(DATE_FORMAT_ERROR_MSG);
        }
        return new DuringCommand(localDate);
    }

    private Command handleFind(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException(FIND_GUIDE_MSG);
        }
        String keyword = body.split(" ")[0].trim();
        return new FindCommand(keyword);
    }

    private Command handleAlias(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException(ALIAS_GUIDE_MSG);
        }
        String alias = body.split(" ")[0].trim();
        CommandInfo cmd;
        try {
            cmd = CommandInfo.valueOf(body.split(" ")[1].trim());
        } catch (IllegalArgumentException e) {
            throw new DookException(ALIAS_GUIDE_MSG);
        }
        return new AddAliasCommand(alias, cmd);
    }
}
