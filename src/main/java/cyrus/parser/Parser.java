package cyrus.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cyrus.commands.AddDeadlineCommand;
import cyrus.commands.AddEventCommand;
import cyrus.commands.AddTodoCommand;
import cyrus.commands.ByeCommand;
import cyrus.commands.Command;
import cyrus.commands.CommandType;
import cyrus.commands.DeleteTaskCommand;
import cyrus.commands.FindTaskCommand;
import cyrus.commands.ListTasksCommand;
import cyrus.commands.MarkTaskCommand;
import cyrus.commands.UnknownCommand;
import cyrus.commands.UnmarkTaskCommand;
import cyrus.commands.ViewStatisticsCommand;
import cyrus.tasks.TaskList;

/**
 * Parses user input into a command format. See {@code ParseInfo} for more information about the
 * parsed form.
 */
public class Parser {
    /**
     * Character(s) that come before an option is given.
     */
    private static final String OPTION_LEAD = "/";

    /**
     * Dispatches {@code Command} given a {@code taskList} and {@code parseInfo} about the incoming
     * string command.
     *
     * @param taskList  {@code TaskList} of tasks to provide to the command when running.
     * @param parseInfo {@code ParseInfo} parsed from command.
     * @return appropriate {@code Command}.
     */
    public Command dispatchCommand(TaskList taskList, ParseInfo parseInfo) {
        switch (parseInfo.getCommandType()) {
        case ADD_TODO:
            return new AddTodoCommand(taskList, parseInfo);
        case ADD_EVENT:
            return new AddEventCommand(taskList, parseInfo);
        case ADD_DEADLINE:
            return new AddDeadlineCommand(taskList, parseInfo);
        case BYE:
            return new ByeCommand(taskList, parseInfo);
        case FIND_TASK:
            return new FindTaskCommand(taskList, parseInfo);
        case DELETE_TASK:
            return new DeleteTaskCommand(taskList, parseInfo);
        case LIST_TASKS:
            return new ListTasksCommand(taskList, parseInfo);
        case MARK_TASK:
            return new MarkTaskCommand(taskList, parseInfo);
        case UNMARK_TASK:
            return new UnmarkTaskCommand(taskList, parseInfo);
        case VIEW_STATISTICS:
            return new ViewStatisticsCommand(taskList, parseInfo);
        default:
            return new UnknownCommand(taskList, parseInfo);
        }
    }

    /**
     * Performs simple parsing on the user input where we read tokens in while no /[word] is
     * encountered.
     *
     * @param line Line to parse.
     */
    public ParseInfo parse(String line) {
        String input = line.trim();
        if (input.equals("")) {
            return ParseInfo.EMPTY;
        }

        String[] parts = input.split("\\s+");
        List<String> argumentParts = new ArrayList<>();
        // Parse the argument first
        int i = 1;
        while (i < parts.length) {
            if (isPartValidOption(parts[i])) {
                break;
            }
            argumentParts.add(parts[i++]);
        }

        String argument = String.join(" ", argumentParts);

        HashMap<String, String> options = new HashMap<>();
        while (i < parts.length) {
            String key = "";
            List<String> acc = new ArrayList<>();
            if (isPartValidOption(parts[i])) {
                key = parts[i].substring(1);
            }

            while (++i < parts.length && !(isPartValidOption(parts[i]))) {
                acc.add(parts[i]);
            }

            options.put(key, String.join(" ", acc));
        }

        return new ParseInfo(CommandType.fromString(parts[0]), argument, options);
    }

    /**
     * Checks if {@code part} is a valid option, i.e. starting with {@code OPTION_LEAD}.
     *
     * @param part part to validate.
     * @return if {@code part} is a valid option.
     */
    private boolean isPartValidOption(String part) {
        return part.startsWith(OPTION_LEAD) && part.length() != 1;
    }
}
