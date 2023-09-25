package parser;

import java.util.ArrayList;

import command.ByeCommand;
import command.Command;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.HelpCommand;
import command.ListCommand;
import command.MarkCommand;
import command.NullCommand;
import command.SortCommand;
import command.TodoCommand;
import command.UnmarkCommand;
import enums.CommandWord;

/**
 * The `Parser` class is responsible for parsing user commands and creating corresponding `Command` objects.
 */
public class Parser {
    /**
     * Parses a raw user command and returns the corresponding `Command` object.
     *
     * @param rawCommand The raw user command.
     * @return A `Command` object representing the parsed command.
     */
    public static Command parse(String rawCommand) {
        switch (CommandWord.commandWordToValueMap(getArgs(rawCommand)[0])) {
        case BYE:
            return new ByeCommand(rawCommand);
        case DEADLINE:
            return new DeadlineCommand(rawCommand);
        case DELETE:
            return new DeleteCommand(rawCommand);
        case EVENT:
            return new EventCommand(rawCommand);
        case FIND:
            return new FindCommand(rawCommand);
        case HELP:
            return new HelpCommand(rawCommand);
        case LIST:
            return new ListCommand(rawCommand);
        case MARK:
            return new MarkCommand(rawCommand);
        case UNMARK:
            return new UnmarkCommand(rawCommand);
        case SORT:
            return new SortCommand(rawCommand);
        case TODO:
            return new TodoCommand(rawCommand);
        default:
            return new NullCommand(rawCommand);
        }
    }

    /**
     * Splits a raw user command into individual arguments.
     *
     * @param rawCommand The raw user command.
     * @return An array of individual arguments.
     */
    public static String[] getArgs(String rawCommand) {
        assert rawCommand != null : "raw command cannot be null";

        ArrayList<String> result = new ArrayList<>();

        String[] words = rawCommand.trim().split(" ");
        if (words.length == 0) {
            return result.toArray(new String[0]);
        }

        String mainCommand = words[0];
        assert mainCommand != null : "main command cannot be null";

        StringBuilder subCommand = new StringBuilder();

        result.add(mainCommand);
        for (int i = 1; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.startsWith("/")) {
                if (!subCommand.toString().trim().isEmpty()) {
                    result.add(subCommand.toString().trim());
                }
                result.add(currentWord);
                subCommand = new StringBuilder();
            } else {
                subCommand.append(" ").append(currentWord);
            }
        }

        if (!subCommand.toString().trim().isEmpty()) {
            result.add(subCommand.toString().trim());
        }

        return result.toArray(new String[0]);
    }
}
