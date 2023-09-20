package slay;

import slay.command.*;
import slay.exception.EmptyArgumentException;
import slay.exception.IncorrectDescriptionFormatException;
import slay.task.Deadline;
import slay.task.Event;
import slay.task.ToDo;

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
        try {
            String[] seperated = userInput.split(" ", 2);
            String commandWord = seperated[0];
            String arguments = "";
            if (seperated.length > 1) {
                arguments += seperated[1];
            }

            switch (commandWord) {

            case ListCommand.COMMAND_WORD:
                return new ListCommand();

            case FindCommand.COMMAND_WORD:
                return prepareFind(arguments);

            case AddCommand.COMMAND_WORD:
                return prepareAdd(arguments);

            case DeleteCommand.COMMAND_WORD:
                return prepareDelete(arguments);

            case MarkCommand.COMMAND_WORD:
                return prepareMark(arguments);

            case UnmarkCommand.COMMAND_WORD:
                return prepareUnmark(arguments);

            case TagCommand.COMMAND_WORD:
                return prepareTag(arguments);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand();

            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
            }
        } catch (EmptyArgumentException e) {
            return new IncorrectCommand(Message.MESSAGE_EMPTY_ARGUMENT);
        }
    }

    private Command prepareFind(String args) throws EmptyArgumentException {
        if (args.isEmpty()) {
            throw new EmptyArgumentException();
        } else {
            return new FindCommand(args);
        }
    }
    /**
     * Parses arguments in the context of the add task command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args) {
        try {
            final Matcher matcher = TASK_ARGS_FORMAT.matcher(args.trim());
            if (!matcher.matches()) {
                return new IncorrectCommand(Message.MESSAGE_INCORRECT_ADD_FORMAT
                        + AddCommand.MESSAGE_USAGE);
            }

            String type = matcher.group("type");
            String description = matcher.group("description");

            switch (type) {
                case ToDo.TYPE:
                    ToDo todo = new ToDo(description);
                    return new AddCommand(todo);

                case Deadline.TYPE:
                    String[] parsedDeadlineArgs = parseDeadlineArgs(description);
                    Deadline deadline = new Deadline(
                            parsedDeadlineArgs[0],
                            parsedDeadlineArgs[1],
                            parsedDeadlineArgs[2]);
                    return new AddCommand(deadline);

                case Event.TYPE:
                    String[] parsedEventArgs = parseEventArgs(description);
                    Event event = new Event(
                            parsedEventArgs[0],
                            parsedEventArgs[1],
                            parsedEventArgs[2],
                            parsedEventArgs[3],
                            parsedEventArgs[4]);
                    return new AddCommand(event);

                default:
                    return new IncorrectCommand(Message.MESSAGE_INCORRECT_ADD_TASK_KEYWORD
                            + AddCommand.MESSAGE_USAGE);
            }
        } catch (IncorrectDescriptionFormatException e) {
            return new IncorrectCommand(Message.MESSAGE_INCORRECT_ADD_FORMAT
                    + AddCommand.MESSAGE_USAGE);
        }
    }

    /**
     * Parses arguments in the context of the add deadline command.
     *
     * @param description full command args string containing deadline description,
     *                    deadline date and time.
     * @return a String array of the parsed command
     */
    private String[] parseDeadlineArgs(String description) throws IncorrectDescriptionFormatException {
        final Matcher matcherDeadline = DEADLINE_DES_FORMAT.matcher(description.trim());
        if (!matcherDeadline.matches()) {
            throw new IncorrectDescriptionFormatException();
        }

        String deadlineDes = matcherDeadline.group("description");
        String ddl = matcherDeadline.group("deadline");
        String[] ddlSplit = ddl.split(" ");
        String date = ddlSplit[0];
        String time = ddlSplit[1];

        String[] parsedArgs = {deadlineDes, date, time};
        return parsedArgs;
    }

    /**
     * Parses arguments in the context of the add event command.
     *
     * @param description full command args string containing event description,
     *                    event start date and time, event end date and time.
     * @return a String array of the parsed command
     */
    private String[] parseEventArgs(String description) throws IncorrectDescriptionFormatException {
        final Matcher matcherEvent = EVENT_DES_FORMAT.matcher(description.trim());
        if (!matcherEvent.matches()) {
            throw new IncorrectDescriptionFormatException();
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

        String[] parsedArgs = {eventDes, fromDate, fromTime, toDate, toTime};

        return parsedArgs;
    }

    /**
     * Parses arguments in the context of the delete task command.
     *
     * @param args command args string which is supposed to be an integer
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

    /**
     * Parses arguments in the context of the mark task command.
     *
     * @param args command args string which is supposed to be an integer
     * @return the prepared command
     */
    private Command prepareMark(String args) {
        int targetVisibleIndex = Integer.parseInt(args);
        return new MarkCommand(targetVisibleIndex);
    }

    /**
     * Parses arguments in the context of the unmark task command.
     *
     * @param args command args string which is supposed to be an integer
     * @return the prepared command
     */
    private Command prepareUnmark(String args) {
        int targetVisibleIndex = Integer.parseInt(args);
        return new UnmarkCommand(targetVisibleIndex);
    }

    private Command prepareTag(String args) {
        String[] split = args.split(" ");
        int targetVisibleIndex = Integer.parseInt(split[0]);
        String label = split[1];
        return new TagCommand(targetVisibleIndex, label);
    }
}
