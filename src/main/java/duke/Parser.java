package duke;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
/**
 * Encapsulates the parsing mechanism of the chat bot.
 */
public class Parser {
    private static final String EMPTY_MARK_QUERY = "☹ OOPS!!! "
            + "You did not specify which task you want to %s.\n"
            + "Please use this syntax:\n %s <index>\n";
    private static final String EMPTY_EVENT_DESC = "☹ OOPS!!! "
            + "The description of a event cannot be empty.\nPlease use this syntax:\n"
            + "event <desc> /from <start> /to <end>\n";
    private static final String EMPTY_DEADLINE_DESC = "☹ OOPS!!! "
            + "The description of a deadline cannot be empty.\n"
            + "Please use this syntax:\n"
            + "deadline <desc> /by <deadline>\n";
    private static final String EMPTY_TODO_DESC = "☹ OOPS!!! "
            + "The description of a todo cannot be empty.\n";
    private static final String NO_VALID_COMMAND = "☹ OOPS!!! "
            + "I'm sorry, but I don't know what that means :-(\n";
    private static final String INVALID_DATE_FORMAT = "☹ OOPS!!! "
            + "Invalid date/time format.\n"
            + "Please format your start/end times as \"yyyy-MM-dd HHmm\" in 24 hr format";
    private static final String INVALID_DATE_ORDER = "☹ OOPS!!! "
            + "Your end date/time is before your start date/time!";
    private static final String EMPTY_FIND_QUERY = "☹ OOPS!!! "
            + "What are you finding?";
    public Command parse(String s) throws ParserException {
        String input = s.trim();
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new DisplayListCommand();
        } else {
            String[] splitInput = input.split(" +", 2);
            switch (splitInput[0]) {
                case "mark":
                //fall through
                case "unmark":
                //fall through
                case "delete":
                    return handleSingleQuery(splitInput);
                case "event":
                    return createEvent(splitInput);
                case "deadline":
                    return createDealine(splitInput);
                case "todo":
                    return createTodo(splitInput);
                case "find":
                    return createFind(splitInput);
                default:
                    throw new ParserException(NO_VALID_COMMAND);
            }
        }
    }
    
    /**
     * Handles single query string commands.
     * Eg. delete 1, mark 1, unmark 1
     * 
     * @param splitInput The array after splitting the command from query String.
     * @return The Command given
     * @throws ParserException Error when parsing the user input
     */
    private Command handleSingleQuery(String[] splitInput) throws ParserException{
        if (splitInput.length == 1) {
                    throw new ParserException(String.format(EMPTY_MARK_QUERY, splitInput[0],
                            splitInput[0]));
        } else {
            try {
                int idx = Integer.parseInt(splitInput[1]);
                switch (splitInput[0]) {
                case "mark":
                    return new MarkCommand(idx);
                case "unmark":
                    return new UnmarkCommand(idx);
                case "delete":
                    return new DeleteCommand(idx);
                default:
                    throw new ParserException("Command not found");
                }
            } catch (NumberFormatException e) {
                throw new ParserException("Please input a valid number for your index!");
            }
        }
    }

    /**
     * Creates an Event
     * 
     * @param splitInputThe array after splitting the command from query String.
     * @return The Command given
     * @throws ParserException Error when parsing the user input
     */
    private Command createEvent(String[] splitInput) throws ParserException {
        if (splitInput.length < 2) {
            throw new ParserException(EMPTY_EVENT_DESC);
        } else {
            String[] secondSplit = splitInput[1].split(" +/from +");
            if (secondSplit.length != 2) {
                throw new ParserException("Incorrect syntax!\n"
                        + "Please use this syntax:\n"
                        + "event <desc> /from <start> /to <end>");
            }
            String[] thirdSplit = secondSplit[1].split(" +/to +");
            if (thirdSplit.length != 2) {
                throw new ParserException("Incorrect syntax!\n"
                        + "Please use this syntax:\n"
                        + "event <desc> /from <start> /to <end>");
            }
            try {
                LocalDateTime start = LocalDateTime.parse(thirdSplit[0], DukeEnvironmentConstants.FORMATTER1);
                LocalDateTime end = LocalDateTime.parse(thirdSplit[1], DukeEnvironmentConstants.FORMATTER1);
                if (end.isBefore(start)) {
                    throw new ParserException(INVALID_DATE_ORDER);
                }
            } catch (DateTimeParseException e) {
                throw new ParserException(INVALID_DATE_FORMAT);
            }
            return new AddToListCommand(DukeEnvironmentConstants.TaskType.EVENT,
                    new String[]{secondSplit[0], thirdSplit[0], thirdSplit[1]});
        }
    }

    /**
     * Creates an Deadline
     * 
     * @param splitInputThe array after splitting the command from query String.
     * @return The Command given
     * @throws ParserException Error when parsing the user input
     */
    private Command createDealine(String[] splitInput) throws ParserException {
        if (splitInput.length < 2) {
                    throw new ParserException(EMPTY_DEADLINE_DESC);
        } else {
            String[] secondSplit = splitInput[1].split(" +/by +", 2);
            if (secondSplit.length != 2) {
                throw new ParserException("Incorrect syntax!\n"
                        + "Please use this syntax:\n"
                        + "deadline <desc> /by <deadline>");
            }
            try {
                LocalDateTime.parse(secondSplit[1], DukeEnvironmentConstants.FORMATTER1);
            } catch (DateTimeParseException e) {
                throw new ParserException(INVALID_DATE_FORMAT);
            }
            return new AddToListCommand(DukeEnvironmentConstants.TaskType.DEADLINE,
                    new String[]{secondSplit[0], secondSplit[1]});
        }
    }

    /**
     * Creates an TodoCommand
     * 
     * @param splitInputThe array after splitting the command from query String.
     * @return The Command given
     * @throws ParserException Error when parsing the user input
     */
    private Command createTodo(String[] splitInput) throws ParserException {
        if (splitInput.length < 2) {
            throw new ParserException(EMPTY_TODO_DESC);
        } else {
            return new AddToListCommand(DukeEnvironmentConstants.TaskType.TODO, new String[]{splitInput[1]});
        }
    }

    /**
     * Creates a FindCommand
     * 
     * @param splitInputThe array after splitting the command from query String.
     * @return The Command given
     * @throws ParserException Error when parsing the user input
     */
    private Command createFind(String[] splitInput) throws ParserException {
        if (splitInput.length < 2) {
            throw new ParserException(EMPTY_FIND_QUERY);
        } else {
            return new FindCommand(splitInput[1]);
        }
    }
}
