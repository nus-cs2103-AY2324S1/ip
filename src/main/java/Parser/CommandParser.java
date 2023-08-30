package Parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Commands.*;

public class CommandParser {

    public static String extractCommand(String input) {
        String[] words = input.split(" ", 2);
        if (words.length > 0) {
            return words[0].toLowerCase();
        }
        return "";
    }

    public Command parse(String input) {
        String command = extractCommand(input);
        TaskParser taskParser = new TaskParser();

        switch (command) {
            case "mark":
                Matcher markMatcher = Pattern.compile(MarkCommand.MARK_PATTERN).matcher(input);
                if (markMatcher.matches()) {
                    int number = Integer.parseInt(markMatcher.group(1)); // Extract the number
                    return new MarkCommand(number);
                } else {
                    return new InvalidCommand("Invalid mark command format.");
                }
            case "unmark":
                Matcher unmarkMatcher = Pattern.compile(UnmarkCommand.UNMARK_PATTERN).matcher(input);
                if (unmarkMatcher.matches()) {
                    int number = Integer.parseInt(unmarkMatcher.group(1)); // Extract the number
                    return new UnmarkCommand(number);
                } else {
                    return new InvalidCommand("Invalid unmark command format.");
                }
            case "delete":
                Matcher deleteMatcher = Pattern.compile(DeleteCommand.DELETE_PATTERN).matcher(input);
                if (deleteMatcher.matches()) {
                    int number = Integer.parseInt(deleteMatcher.group(1)); // Extract the number
                    return new DeleteCommand(number);
                } else {
                    return new InvalidCommand("Invalid delete command format.");
                }
            case "event":
                return new AddEventCommand(taskParser.parseTask(input));

            case "todo":
                return new AddTodoCommand(taskParser.parseTask(input));

            case "deadline":
                return new AddDeadlineCommand(taskParser.parseTask(input));

            default:
                return new HelpCommand();
        }
    }

}
