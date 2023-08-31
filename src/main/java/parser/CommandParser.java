package parser;

import commands.AddDeadlineCommand;
import commands.AddEventCommand;
import commands.AddTodoCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The CommandParser is responsible for parsing input and generating the Command objects.
 * It examines the input to determine the type of command and returns the appropriate instance.
 */
public class CommandParser {

    /**
     * Extracts the command keyword from the input string.
     *
     * @param input The input string from the user.
     * @return The command keyword.
     */
    private static String extractCommand(String input) {
        String[] words = input.split(" ", 2);
        if (words.length > 0) {
            return words[0].toLowerCase();
        }
        return "";
    }

    /**
     * Extracts the value (e.g., task position) from the input string.
     *
     * @param input The input string from the user.
     * @return The extracted value.
     */
    private static int extractValue(String input) {
        String[] parts = input.split("\\s+");
        return Integer.parseInt(parts[1]);
    }

    /**
     * Parses the user input and generates the appropriate Command instance.
     *
     * @param input The input string from the user.
     * @return The corresponding Command instance.
     */
    public static Command parse(String input) {
        String command = extractCommand(input);
        TaskParser taskParser = new TaskParser();

        switch (command) {
            case "mark" -> {
                Matcher markMatcher = Pattern.compile(MarkCommand.MARK_PATTERN).matcher(input);
                if (markMatcher.matches()) {
                    int pos = extractValue(input);
                    return new MarkCommand(pos);
                } else {
                    return new InvalidCommand("Invalid mark command format.");
                }
            }
            case "unmark" -> {
                Matcher unmarkMatcher = Pattern.compile(UnmarkCommand.UNMARK_PATTERN).matcher(input);
                if (unmarkMatcher.matches()) {
                    int pos = extractValue(input);
                    return new UnmarkCommand(pos);
                } else {
                    return new InvalidCommand("Invalid unmark command format.");
                }
            }
            case "delete" -> {
                Matcher deleteMatcher = Pattern.compile(DeleteCommand.DELETE_PATTERN).matcher(input);
                if (deleteMatcher.matches()) {
                    int pos = extractValue(input);
                    return new DeleteCommand(pos);
                } else {
                    return new InvalidCommand("Invalid delete command format.");
                }
            }
            case "find" -> {
                String keyword = input.substring("find".length()).trim();
                return new FindCommand(keyword);
            }
            case "event" -> {
                return new AddEventCommand(taskParser.parseTask(input));
            }
            case "todo" -> {
                return new AddTodoCommand(taskParser.parseTask(input));
            }
            case "deadline" -> {
                return new AddDeadlineCommand(taskParser.parseTask(input));
            }
            case "bye" -> {
                return new ExitCommand();
            }
            case "list" -> {
                return new ListCommand();
            }
            default -> {
                return new HelpCommand();
            }
        }
    }
}
