package duke.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.commands.*;

public class CommandParser {
    private static final Pattern COMMAND_PATTERN = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Pattern TODO_ARGS_PATTERN = Pattern.compile("(?<name>\\S.*)");
    private static final Pattern DEADLINE_ARGS_PATTERN = Pattern.compile("(?<name>\\S+.*)( /by )(?<time>\\S.*)");
    private static final Pattern EVENT_ARGS_PATTERN = Pattern.compile("(?<name>\\S+.*)( /from )(?<startTime>\\S.*)( \\/to )(?<endTime>\\S.*)");
    public CommandParser() { }

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
            case FindCommand.COMMAND_PHRASE:
                return new FindCommand(args.trim());
            case ByeCommand.COMMAND_PHRASE:
                return new ByeCommand();
            case DeleteCommand.COMMAND_PHRASE:
                return new DeleteCommand(Integer.parseInt(args.trim()));
            case TodoCommand.COMMAND_PHRASE:
                Matcher tdMatcher =TODO_ARGS_PATTERN.matcher(args.trim());
                if (tdMatcher.find()) {
                    return new TodoCommand(args.trim());
                } else {
                    return new InvalidCommand("Name cannot be empty!");
                }
            case EventCommand.COMMAND_PHRASE:
                Matcher evMatcher = EVENT_ARGS_PATTERN.matcher(args.trim());
                if (evMatcher.find()) {
                    return new EventCommand(evMatcher.group("name"), TimeParser.parseTime(evMatcher.group("startTime").trim()), TimeParser.parseTime(evMatcher.group("endTime").trim()));
                } else {
                    return new InvalidCommand("Bad event arguments!");
                }
            case DeadlineCommand.COMMAND_PHRASE:
                Matcher ddlMatcher = DEADLINE_ARGS_PATTERN.matcher(args.trim());
                if (ddlMatcher.find()) {
                    return new DeadlineCommand(ddlMatcher.group("name"), TimeParser.parseTime(ddlMatcher.group("time").trim()));
                } else {
                    return new InvalidCommand("Bad deadline argument!");
                }
        }

        return new InvalidCommand("Command not found");
    }

}
