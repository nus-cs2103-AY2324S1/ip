package parser;

import command.*;
import enums.CommandWord;

import java.util.ArrayList;

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
            case LIST:
                return new ListCommand(rawCommand);
            case MARK:
                return new MarkCommand(rawCommand);
            case UNMARK:
                return new UnmarkCommand(rawCommand);
            case DELETE:
                return new DeleteCommand(rawCommand);
            case TODO:
                return new TodoCommand(rawCommand);
            case DEADLINE:
                return new DeadlineCommand(rawCommand);
            case EVENT:
                return new EventCommand(rawCommand);
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
        ArrayList<String> result = new ArrayList<>();
        String[] words = rawCommand.split("\\s+");
        String mainCommand = words[0];
        StringBuilder subCommand = new StringBuilder();

        result.add(mainCommand);
        for (int i = 1; i < words.length; i++) {
            String currentWord = words[i];
            if (currentWord.startsWith("/")) {
                result.add(subCommand.toString().trim());
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
