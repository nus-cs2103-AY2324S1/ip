package tong;

import tong.command.*;
import tong.exception.TaskNotFoundException;
import tong.task.Deadline;
import tong.task.Event;
import tong.task.ToDo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input.
 */
public class Parser {
    public static final Pattern TASK_ARGS_FORMAT =
            Pattern.compile("(?<type>\\S+)\\s+(?<description>.+)$");

    public static final Pattern DEADLINE_DES_FORMAT =
            Pattern.compile("(?<description>.+)\\s+"
                    + "/by\\s+"
                    + "(?<deadline>\\S+\\s+\\S+)$");

    public static final Pattern EVENT_DES_FORMAT =
            Pattern.compile("^(?<description>.+)\\s+"
                    + "/from\\s+"
                    + "(?<start>\\S+\\s+\\S+)\\s+"
                    + "/to\\s+"
                    + "(?<end>\\S+\\s+\\S+)$");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parse(String userInput) {
        String[] seperated = userInput.split(" ", 2);
        String commandWord = seperated[0];
        String arguments = "";
        if (seperated.length > 1) {
            arguments += seperated[1];
        }

        switch (commandWord) {

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case AddCommand.COMMAND_WORD:
                return prepareAdd(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case MarkCommand.COMMAND_WORD:
                return prepareMark(arguments);

            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
        }
    }

    /**
     * Parses arguments in the context of the add task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args) {
        final Matcher matcher = TASK_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            return new IncorrectCommand("Sorry, I don't know this type of tasks:(\n"
                    + "You may choose from what I have:D\n"
                    + AddCommand.MESSAGE_USAGE);
        }

        String type = matcher.group("type");
        String description = matcher.group("description");

        switch (type) {
            case ToDo.TYPE:
                ToDo todo = new ToDo(description);
                return new AddCommand(todo);

            case Deadline.TYPE:
                final Matcher matcherDeadline = DEADLINE_DES_FORMAT.matcher(description.trim());
                if (!matcherDeadline.matches()) {
                    return new IncorrectCommand("Invalid Format:(\n"
                            + "Could you talk in the ways that I can understand:D\n"
                            + AddCommand.MESSAGE_USAGE);
                }

                String deadlineDes = matcherDeadline.group("description");
                String ddl = matcherDeadline.group("deadline");
                String[] ddlSplit = ddl.split(" ");
                String date = ddlSplit[0];
                String time = ddlSplit[1];

                Deadline deadline = new Deadline(deadlineDes, date, time);
                return new AddCommand(deadline);

            case Event.TYPE:
                final Matcher matcherEvent = EVENT_DES_FORMAT.matcher(description.trim());
                if (!matcherEvent.matches()) {
                    return new IncorrectCommand("Invalid Format:(\n"
                            + "Could you talk in the ways that I can understand:D\n"
                            + AddCommand.MESSAGE_USAGE);
                }

                String eventDes = matcherEvent.group("description");
                String start = matcherEvent.group("start");
                String end = matcherEvent.group("end");
                String[] startSplit = start.split(" ");
                String[] endSplit = end.split(" ");
                String fromDate = startSplit[0];
                String fromTime = startSplit[1];
                String toDate = endSplit[0];
                String toTime = endSplit[1];

                Event event = new Event(eventDes, fromDate, fromTime, toDate, toTime);
                return new AddCommand(event);

            default:
                return new IncorrectCommand("Sorry, I don't know this type of tasks:(\n"
                        + "You may choose from what I have:D\n"
                        + AddCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            int targetVisibleIndex = Integer.parseInt(args);
            return new DeleteCommand(targetVisibleIndex);
        } catch (NumberFormatException e) {
            return new IncorrectCommand("Invalid Format:(\n"
                    + "Could you talk in the ways that I can understand:D\n"
                    + DeleteCommand.MESSAGE_USAGE);
        }
    }

    private Command prepareMark(String args) {
        int targetVisibleIndex = Integer.parseInt(args);
        return new MarkCommand(targetVisibleIndex);
    }

    private Command prepareUnmark(String args) {
        int targetVisibleIndex = Integer.parseInt(args);
        return new UnmarkCommand(targetVisibleIndex);
    }
}
