package brandon.chatbot.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import brandon.chatbot.commands.AddDeadlineCommand;
import brandon.chatbot.commands.AddEventCommand;
import brandon.chatbot.commands.AddTodoCommand;
import brandon.chatbot.commands.Command;
import brandon.chatbot.commands.DeleteCommand;
import brandon.chatbot.commands.ExitCommand;
import brandon.chatbot.commands.FindCommand;
import brandon.chatbot.commands.ListCommand;
import brandon.chatbot.commands.MarkCommand;
import brandon.chatbot.commands.UnknownCommand;
import brandon.chatbot.commands.UnmarkCommand;
import brandon.chatbot.common.DukeException;

/**
 * Represents the parser that parses the user inputs.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    public static final Pattern TODO_FIND_ARGS_FORMAT = Pattern.compile("(?<name>.*)");
    public static final Pattern DEADLINE_ARGS_FORMAT = Pattern.compile("(?<name>[^/]+)/by(?<deadline>[^/]+)");
    public static final Pattern EVENT_ARGS_FORMAT = Pattern.compile("(?<name>[^/]+)"
            + "/from(?<from>[^/]+)"
            + "/to(?<to>[^/]+)");

    /**
     * Parses the user input string and returns a command according to it.
     *
     * @param userInput is the direct input the user types in.
     * @return Command to be run.
     */
    public Command parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new UnknownCommand();
        }

        final String commandWord = matcher.group("commandWord").toLowerCase();
        final String arguments = matcher.group("arguments");
        System.out.println(commandWord);
        switch (commandWord) {
        case "find":
            return prepareFind(arguments);
        case "todo":
            return prepareTodo(arguments);
        case "delete":
            return prepareDelete(arguments);
        case "list":
            return new ListCommand();
        case "mark":
            return prepareMark(arguments);
        case "unmark":
            return prepareUnmark(arguments);
        case "deadline":
            return prepareDeadline(arguments);
        case "event":
            return prepareEvent(arguments);
        case "bye":
            return new ExitCommand();
        default:
            break;
        }
        return null;
    }

    /**
     * Returns a command that finds the task with the same title passed in the argument.
     *
     * @param arg is the title passed by the user.
     * @return the command that finds the task with the given name.
     */
    private Command prepareFind(String arg) {
        final Matcher matcher = TODO_FIND_ARGS_FORMAT.matcher(arg.trim());
        if (!matcher.matches()) {
            return new UnknownCommand();
        }
        return new FindCommand(matcher.group("name").strip());
    }

    private Command prepareTodo(String args) {
        final Matcher matcher = TODO_FIND_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new UnknownCommand();
        }
        try {
            String grp = matcher.group("name");
            System.out.println(grp);
            return new AddTodoCommand(grp);
        } catch (DukeException e) {
            return new UnknownCommand();
        }
    }
    private Command prepareDeadline(String args) {
        final Matcher matcher = DEADLINE_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new UnknownCommand();
        }
        try {
            return new AddDeadlineCommand(matcher.group("name"), matcher.group("deadline").strip());
        } catch (DukeException e) {
            return new UnknownCommand();
        }
    }
    private Command prepareEvent(String args) {
        final Matcher matcher = EVENT_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            return new UnknownCommand();
        }
        try {
            System.out.println("to match: " + matcher.group("to").strip());
            return new AddEventCommand(
                    matcher.group("name"),
                    matcher.group("from").strip(),
                    matcher.group("to").strip()
            );
        } catch (DukeException e) {
            return new UnknownCommand();
        }
    }
    private Command prepareDelete(String args) {
        final int index = Integer.parseInt(args.trim());
        return new DeleteCommand(index);
    }

    private Command prepareMark(String args) {
        final int index = Integer.parseInt(args.trim());
        return new MarkCommand(index);
    }

    private Command prepareUnmark(String args) {
        final int index = Integer.parseInt(args.trim());
        return new UnmarkCommand(index);
    }
}
