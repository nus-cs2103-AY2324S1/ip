package dook.services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import dook.DookException;
import dook.command.AddTaskCommand;
import dook.command.AfterCommand;
import dook.command.BeforeCommand;
import dook.command.ByeCommand;
import dook.command.Command;
import dook.command.CommandInfo;
import dook.command.DeleteTaskCommand;
import dook.command.DuringCommand;
import dook.command.FindCommand;
import dook.command.InvalidCommand;
import dook.command.ListCommand;
import dook.command.MarkCommand;
import dook.command.SaveCommand;
import dook.command.UnmarkCommand;
import dook.task.Deadline;
import dook.task.Event;
import dook.task.Task;
import dook.task.Todo;

/**
 * Responsible for parsing plaintext commands and returning appropriate
 * Command objects.
 */
public class Parser {
    private static CommandInfo parseKeyword(String keyword) {
        try {
            return CommandInfo.valueOf(keyword);
        } catch (IllegalArgumentException e) {
            return CommandInfo.invalid;
        }
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
        default:
            return new InvalidCommand();
        }
    }
    private Command handleMark(String body, boolean value) throws DookException {
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0].trim());
        } catch (NumberFormatException e) {
            throw new DookException(String.format("Usage: %s [task number]", value ? "mark" : "unmark"));
        }
        return value
                ? new MarkCommand(index)
                : new UnmarkCommand(index);
    }
    private Command handleToDo(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException("Usage: todo [name]");
        }
        Task task = new Todo(body.trim(), false);
        return new AddTaskCommand(task);
    }
    private Command handleDeadline(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException("Usage: deadline [name] /by [time].");
        }

        String[] tmp = body.split("/by", 2);
        if (tmp.length <= 1) {
            throw new DookException("Usage: deadline [name] /by [time].");
        }

        String desc = tmp[0].trim();
        String by = tmp[1].trim();
        if (desc.isBlank() || by.isBlank()) {
            throw new DookException("Some information is missing!\n"
                    + "Usage: deadline [name] /by [time].");
        }
        Task task = new Deadline(desc, by, false);
        return new AddTaskCommand(task);
    }
    private Command handleEvent(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException("Usage: event [name] /from [start] /to [end].");
        }
        String[] tmp1 = body.split("/from", 2);
        if (tmp1.length <= 1) {
            throw new DookException("Usage: event [name] /from [start] /to [end].");
        }

        String desc = tmp1[0].trim();

        String[] tmp2 = tmp1[1].split("/to", 2);
        if (tmp2.length <= 1) {
            throw new DookException("Usage: event [name] /from [start] /to [end].");
        }
        String from = tmp2[0].trim();
        String to = tmp2[1].trim();
        if (desc.isBlank() || from.isBlank() || to.isBlank()) {
            throw new DookException("Some information is missing!\n"
                    + "Usage: event [name] /from [start] /to [end].");
        }

        Task task = new Event(desc, from, to, false);
        return new AddTaskCommand(task);
    }
    private Command handleDelete(String body) throws DookException {
        int index;
        try {
            index = Integer.parseInt(body.split(" ", 2)[0]);
        } catch (NumberFormatException e) {
            throw new DookException("Usage: delete [task number]");
        }
        return new DeleteTaskCommand(index);
    }
    private Command handleBefore(String body) throws DookException {
        LocalDate localDate;
        try {
            localDate = TimeProcessor.getLocalDateFromString(body.split(" ", 2)[0].trim());
        } catch (DateTimeParseException e) {
            throw new DookException("Improper date format.");
        }
        return new BeforeCommand(localDate);
    }

    private Command handleAfter(String body) throws DookException {
        LocalDate localDate;
        try {
            localDate = TimeProcessor.getLocalDateFromString(body.split(" ", 2)[0].trim());
        } catch (DateTimeParseException e) {
            throw new DookException("Improper date format.");
        }
        return new AfterCommand(localDate);
    }

    private Command handleDuring(String body) throws DookException {
        LocalDate localDate;
        try {
            localDate = TimeProcessor.getLocalDateFromString(body.split(" ", 2)[0].trim());
        } catch (DateTimeParseException e) {
            throw new DookException("Improper date format.");
        }
        return new DuringCommand(localDate);
    }

    private Command handleFind(String body) throws DookException {
        if (body.isBlank()) {
            throw new DookException("Usage: find [query]");
        }
        String keyword = body.split(" ")[0].trim();
        return new FindCommand(keyword);
    }
}
