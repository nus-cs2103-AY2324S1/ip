import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
/**
 * Encapsulates the parsing mechanism of the chat bot.
 */
public class Parser {
    private static final String EMPTY_MARK_QUERY = "☹ OOPS!!! You did not specify which task you want to %s.\n"
            + "Please use this syntax:\n %s <index>\n";
    private static final String EMPTY_EVENT_DESC = "☹ OOPS!!! The description of a event cannot be empty.\nPlease use this syntax:\n"
             + "event <desc> /from <start> /to <end>\n";
    private static final String EMPTY_DEADLINE_DESC = "☹ OOPS!!! The description of a deadline cannot be empty.\nPlease use this syntax:\n"
            + "deadline <desc> /by <deadline>\n" ;
    private static final String EMPTY_TODO_DESC = "☹ OOPS!!! The description of a todo cannot be empty.\n";
    private static final String NO_VALID_COMMAND = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n";
    private static final String INVALID_DATE_FORMAT = "☹ OOPS!!! Invalid date/time format.\nPlease format your start/end times as \"yyyy-MM-dd HHmm\" in 24 hr format";
    public Command parse(String s) throws ParserException {
        
        String input = s.trim();
        if (input.equals("bye")) {
            return new ExitCommand();
        } else if (input.equals("list")) {
            return new DisplayListCommand();
        } else {
            String[] splitInput = input.split(" +", 2);
            if (splitInput[0].equals("mark") || splitInput[0].equals("unmark") || splitInput[0].equals("delete")) {
                if (splitInput.length == 1) {
                    throw new ParserException(String.format(EMPTY_MARK_QUERY, splitInput[0],
                            splitInput[0]));
                } else {
                    try {
                        int idx = Integer.parseInt(splitInput[1]);
                        if (splitInput[0].equals("mark")) {
                            return new MarkCommand(idx);
                        } else if (splitInput[0].equals("unmark")) {
                            return new UnmarkCommand(idx);
                        } else {
                            return new DeleteCommand(idx);
                        }
                    } catch (NumberFormatException e) {
                        throw new ParserException("Please input a valid number for your index!");
                    } 
                }
            } else if (splitInput[0].equals("event")) {
                if (splitInput.length < 2) {
                    throw new ParserException(EMPTY_EVENT_DESC);
                } else {
                    String[] secondSplit = splitInput[1].split(" +/from +");
                    if (secondSplit.length != 2) {
                        throw new ParserException("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "event <desc> /from <start> /to <end>");
                    }
                    String[] thirdSplit = secondSplit[1].split(" +/to +");
                    if (thirdSplit.length != 2) {
                        throw new ParserException("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "event <desc> /from <start> /to <end>");
                    }
                    try {
                        LocalDateTime.parse(thirdSplit[0], DukeEnvironmentConstants.FORMATTER1);
                        LocalDateTime.parse(thirdSplit[1],DukeEnvironmentConstants.FORMATTER1);
                    } catch (DateTimeParseException e) {
                        throw new ParserException(INVALID_DATE_FORMAT);
                    }
                    return new AddToListCommand(DukeEnvironmentConstants.taskType.EVENT, new String[]{secondSplit[0], thirdSplit[0], thirdSplit[1]});
                }
            } else if (splitInput[0].equals("deadline")) {
                if (splitInput.length < 2) {
                    throw new ParserException(EMPTY_DEADLINE_DESC);
                } else {
                    String[] secondSplit = splitInput[1].split(" +/by +", 2);
                    if (secondSplit.length != 2) {
                        throw new ParserException("Incorrect syntax!\n" +
                                "Please use this syntax:\n" +
                                "deadline <desc> /by <deadline>");
                    }
                    try {
                        LocalDateTime.parse(secondSplit[1],DukeEnvironmentConstants.FORMATTER1);
                    } catch (DateTimeParseException e) {
                        throw new ParserException(INVALID_DATE_FORMAT);
                    }
                    return new AddToListCommand(DukeEnvironmentConstants.taskType.DEADLINE, new String[]{secondSplit[0], secondSplit[1]});
                }
            } else if (splitInput[0].equals("todo")) {
                if (splitInput.length < 2) {
                    throw new ParserException(EMPTY_TODO_DESC);
                } else {
                    return new AddToListCommand(DukeEnvironmentConstants.taskType.TODO, new String[]{splitInput[1]});
                }
            } else {
                throw new ParserException(NO_VALID_COMMAND);
            }
        }
    }
}
