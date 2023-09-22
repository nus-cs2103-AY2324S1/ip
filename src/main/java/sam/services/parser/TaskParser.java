package sam.services.parser;

import sam.commands.AddDeadlineCommand;
import sam.commands.AddToDoCommand;
import sam.commands.Command;
import sam.commands.IncorrectCommand;
import sam.commands.FindCommand;
import sam.commands.AddEventCommand;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

import static sam.constants.Message.INVALID_COMMAND_FORMAT;

public class TaskParser {

    /**
     * Parses arguments in the context of the add todo task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    public static Command prepareAddTodo(String args) {
        final Pattern TODO_TASK_PATTERN = Pattern.compile("(?<description>.*)");
        final Matcher matcher = TODO_TASK_PATTERN.matcher(args);
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT, AddToDoCommand.MESSAGE_USAGE));
        }
        String description = matcher.group("description").trim();
        if (description.equals("")) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT, AddToDoCommand.MESSAGE_USAGE));
        }
        return new AddToDoCommand(description);
    }

    /**
     * Parses arguments in the context of the add deadline task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    public static Command prepareAddDeadline(String args) {
        final Pattern DEADLINE_TASK_PATTERN = Pattern.compile("(?<description>.*) /by (?<by>.*)");
        final Matcher matcher = DEADLINE_TASK_PATTERN.matcher(args);
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT, AddDeadlineCommand.MESSAGE_USAGE));
        }
        String description = matcher.group("description").trim();
        String by = matcher.group("by").trim();
        return new AddDeadlineCommand(description, DateTimeParser.parseDate(by));
    }

    /**
     * Parses arguments in the context of the add event task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    public static Command prepareAddEvent(String args) {
        final Pattern EVENT_TASK_PATTERN = Pattern.compile("(?<description>.*) /from (?<from>.*) /to (?<to>.*)");
        final Matcher matcher = EVENT_TASK_PATTERN.matcher(args);
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }
        String description = matcher.group("description").trim();
        String from = matcher.group("from").trim();
        String to = matcher.group("to").trim();
        return new AddEventCommand(description, DateTimeParser.parseDate(from), DateTimeParser.parseDate(to));
    }

    /**
     * Parses arguments in the context of the find task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    public static Command prepareFind(String args) {
        final Pattern FIND_TASK_PATTERN = Pattern.compile("(?<keyword>.*)");
        final Matcher matcher = FIND_TASK_PATTERN.matcher(args);
        // Validate arg string format
        if (!matcher.matches()) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        String keyword = matcher.group("keyword").trim();
        if (keyword.equals("")) {
            return new IncorrectCommand(String.format(INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }
        return new FindCommand(keyword);
    }
}
