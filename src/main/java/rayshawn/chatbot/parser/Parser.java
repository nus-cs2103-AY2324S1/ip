package rayshawn.chatbot.parser;

import static rayshawn.chatbot.messages.Messages.INVALID_COMMAND_FORMAT_MESSAGE;
import static rayshawn.chatbot.messages.Messages.INVALID_TASK_NUMBER_MESSAGE;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import rayshawn.chatbot.commands.ByeCommand;
import rayshawn.chatbot.commands.Command;
import rayshawn.chatbot.commands.DeadlineCommand;
import rayshawn.chatbot.commands.DeleteCommand;
import rayshawn.chatbot.commands.EventCommand;
import rayshawn.chatbot.commands.FindCommand;
import rayshawn.chatbot.commands.HelpCommand;
import rayshawn.chatbot.commands.IncorrectCommand;
import rayshawn.chatbot.commands.ListCommand;
import rayshawn.chatbot.commands.MarkCommand;
import rayshawn.chatbot.commands.NoSuchCommand;
import rayshawn.chatbot.commands.ToDoCommand;
import rayshawn.chatbot.commands.UnmarkCommand;
import rayshawn.chatbot.commands.UpdateCommand;
import rayshawn.chatbot.exceptions.ChatBotException;

/**
 * Parses user input.
 */
public class Parser {
    // Adapted from https://github.com/se-edu/addressbook-level2/blob/master/src/seedu/addressbook/parser/Parser.java
    private static final Pattern TASK_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>[1-9]\\d*)");

    private static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)");

    private static final Pattern TODO_TASK_ARGS_FORMAT = Pattern.compile("(?<description>[^/]+)");
    private static final Pattern DEADLINE_TASK_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)"
                    + " /by (?<date>(19|20)\\d{2}(\\/|-)(0[1-9]|1[1,2])(\\/|-)(0[1-9]|[12][0-9]|3[01]))"
            );
    private static final Pattern EVENT_TASK_ARGS_FORMAT =
            Pattern.compile("(?<description>[^/]+)"
                    + " /from (?<date>(19|20)\\d{2}(\\/|-)(0[1-9]|1[1,2])(\\/|-)(0[1-9]|[12][0-9]|3[01]))"
                    + " (?<start>([0-9]|0[0-9]|1[0-2])(AM|PM))"
                    + " /to (?<end>([0-9]|0[0-9]|1[0-2])(AM|PM))"
            );

    private static final Pattern UPDATE_TASK_ARGS_FORMAT =
            Pattern.compile("(?<targetIndex>[1-9]\\d*)"
                + " (?<update>[^/]+)"
                + " (?<info>[^/]+)"
            );

    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        assert userInput != null : "User input should not be null";

        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        assert matcher != null : "Matcher should not be null";

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {
        case ByeCommand.COMMAND_WORD:
            return new ByeCommand();
        case DeadlineCommand.COMMAND_WORD:
            return prepareDeadline(arguments);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);
        case EventCommand.COMMAND_WORD:
            return prepareEvent(arguments);
        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case MarkCommand.COMMAND_WORD:
            return prepareMark(arguments);
        case ToDoCommand.COMMAND_WORD:
            return prepareToDo(arguments);
        case UnmarkCommand.COMMAND_WORD:
            return prepareUnmark(arguments);
        case UpdateCommand.COMMAND_WORD:
            return prepareUpdate(arguments);
        default:
            return new NoSuchCommand();
        }
    }

    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_TASK_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, DeadlineCommand.MESSAGE_USAGE));
        }

        try {
            return new DeadlineCommand(
                    matcher.group("description"),
                    matcher.group("date")
            );
        } catch (ChatBotException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_TASK_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, EventCommand.MESSAGE_USAGE));
        }

        try {
            return new EventCommand(
                    matcher.group("description"),
                    matcher.group("date"),
                    matcher.group("start"),
                    matcher.group("end")
            );
        } catch (ChatBotException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command prepareMark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new MarkCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, MarkCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    private Command prepareToDo(String args) {
        final Matcher matcher = TODO_TASK_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, ToDoCommand.MESSAGE_USAGE));
        }

        try {
            return new ToDoCommand(matcher.group("description"));
        } catch (ChatBotException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command prepareUnmark(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new UnmarkCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, UnmarkCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    private Command prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());

        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, FindCommand.MESSAGE_USAGE));
        }

        try {
            return new FindCommand(matcher.group("keywords"));
        } catch (ChatBotException e) {
            return new IncorrectCommand(e.getMessage());
        }
    }

    private Command prepareUpdate(String args) {
        final Matcher matcher = UPDATE_TASK_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, UpdateCommand.MESSAGE_USAGE));
        }

        try {
            return new UpdateCommand(
                    parseArgsAsDisplayedIndex(matcher.group("targetIndex")),
                    matcher.group("update"),
                    matcher.group("info")
            );
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT_MESSAGE, MarkCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(INVALID_TASK_NUMBER_MESSAGE);
        }
    }

    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = TASK_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

}
