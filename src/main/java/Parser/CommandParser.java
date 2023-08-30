package Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Commands.*;

public class CommandParser {

    private static String extractCommand(String input) {
        String[] words = input.split(" ", 2);
        if (words.length > 0) {
            return words[0].toLowerCase();
        }
        return "";
    }

    private static int extractValue(String input) {
        String[] parts = input.split("\\s+");
        return Integer.parseInt(parts[1]);
    }

    public static Command parse(String input) {
        String command = extractCommand(input);
        TaskParser taskParser = new TaskParser();

        switch (command) {
            case "mark":
                Matcher markMatcher = Pattern.compile(MarkCommand.MARK_PATTERN).matcher(input);
                if (markMatcher.matches()) {
                    int pos = extractValue(input);
                    return new MarkCommand(pos);
                } else {
                    return new InvalidCommand("Invalid mark command format.");
                }
            case "unmark":
                Matcher unmarkMatcher = Pattern.compile(UnmarkCommand.UNMARK_PATTERN).matcher(input);
                if (unmarkMatcher.matches()) {
                    int pos = extractValue(input);
                    return new UnmarkCommand(pos);
                } else {
                    return new InvalidCommand("Invalid unmark command format.");
                }
            case "delete":
                Matcher deleteMatcher = Pattern.compile(DeleteCommand.DELETE_PATTERN).matcher(input);
                if (deleteMatcher.matches()) {
                    int pos = extractValue(input);
                    return new DeleteCommand(pos);
                } else {
                    return new InvalidCommand("Invalid delete command format.");
                }
            case "event":
                return new AddEventCommand(taskParser.parseTask(input));

            case "todo":
                return new AddTodoCommand(taskParser.parseTask(input));

            case "deadline":
                return new AddDeadlineCommand(taskParser.parseTask(input));

            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            default:
                return new HelpCommand();
        }
    }

}
