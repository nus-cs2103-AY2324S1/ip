package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeadlineCommand;
import duke.commands.DeleteCommand;
import duke.commands.EventCommand;
import duke.commands.FindCommand;
import duke.commands.InvalidCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.TodoCommand;
import duke.commands.UnmarkCommand;

/**
 * Parses user input into command.
 * @author Toh Li Yuan (A0255811H)
 */
public class CommandParser {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Pattern TODO_ARGS_PATTERN = Pattern.compile("(?<name>\\S.*)");
    private static final Pattern DEADLINE_ARGS_PATTERN = Pattern.compile("(?<name>\\S+.*)( /by )(?<time>\\S.*)");
    private static final Pattern EVENT_ARGS_PATTERN =
            Pattern.compile("(?<name>\\S+.*)( /from )(?<startTime>\\S.*)( /to )(?<endTime>\\S.*)");
    public CommandParser() { }

    /**
     * Processes a user input to a command.
     *
     * @param input the input string from the user
     * @return the correct command from user input.
     * Returns an Invalid Command if a syntax error in the command is found.
     */
    public Command parseCommand(String input) {
        Matcher matcher = COMMAND_PATTERN.matcher(input);
        if (!matcher.matches()) {
            return new InvalidCommand("Invalid command format");
        }

        String commandWord = matcher.group("commandWord");
        String args = matcher.group("arguments");

        switch (commandWord) {
        case MarkCommand.COMMAND_PHRASE:
            return new MarkCommand(Integer.parseInt(args.trim()));
        case UnmarkCommand.COMMAND_PHRASE:
            return new UnmarkCommand(Integer.parseInt(args.trim()));
        case ListCommand.COMMAND_PHRASE:
            return new ListCommand();
        case ByeCommand.COMMAND_PHRASE:
            return new ByeCommand();
        case FindCommand.COMMAND_PHRASE:
            return new FindCommand(args.trim());
        case DeleteCommand.COMMAND_PHRASE:
            return new DeleteCommand(Integer.parseInt(args.trim()));
        case TodoCommand.COMMAND_PHRASE:
            Matcher tdMatcher = TODO_ARGS_PATTERN.matcher(args.trim());
            if (tdMatcher.find()) {
                return new TodoCommand(args.trim());
            } else {
                return new InvalidCommand("Name cannot be empty!");
            }
        case EventCommand.COMMAND_PHRASE:
            Matcher evMatcher = EVENT_ARGS_PATTERN.matcher(args.trim());
            if (evMatcher.find()) {
                return new EventCommand(evMatcher.group("name"),
                        TimeParser.parseTime(evMatcher.group("startTime").trim()),
                        TimeParser.parseTime(evMatcher.group("endTime").trim()));
            } else {
                return new InvalidCommand("Bad event arguments!");
            }
        case DeadlineCommand.COMMAND_PHRASE:
            Matcher ddlMatcher = DEADLINE_ARGS_PATTERN.matcher(args.trim());
            if (ddlMatcher.find()) {
                return new DeadlineCommand(ddlMatcher.group("name"),
                        TimeParser.parseTime(ddlMatcher.group("time").trim()));
            } else {
                return new InvalidCommand("Bad deadline argument!");
            }
        default:
            return new InvalidCommand("Command not found");
        }
    }

}
