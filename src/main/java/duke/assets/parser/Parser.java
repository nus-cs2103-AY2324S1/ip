package duke.assets.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import duke.assets.commands.ByeCommand;
import duke.assets.commands.CommandAbstract;
import duke.assets.commands.CreateDeadlineCommand;
import duke.assets.commands.CreateEventCommand;
import duke.assets.commands.CreateTodoCommand;
import duke.assets.commands.DeleteCommand;
import duke.assets.commands.FindCommand;
import duke.assets.commands.ListCommand;
import duke.assets.commands.MarkCommand;
import duke.assets.commands.SortCommand;
import duke.assets.commands.UnmarkCommand;
import duke.assets.storage.TaskList;
import duke.dukeexceptions.CorruptDataException;
import duke.dukeexceptions.InvalidCommandException;

/**
 * Parser class for parsing commands by user and data
 */
public class Parser {
    private static final String GENERAL_DATA_REGEX_STRING = "^[TDE] \\| [01] \\| .+";
    private static final String DEADLINE_REGEX_STRING = "^D \\| [01] \\| .+ \\| \\d{4}-\\d{2}-\\d{2}"
            + "($| [0-2][0-9][0-5][0-9]$)";
    private static final String EVENT_REGEX_STRING = "^E \\| [01] \\| .+ \\| \\d{4}-\\d{2}-\\d{2}"
            + "( [0-2][0-9][0-5][0-9] | )- \\d{4}-\\d{2}-\\d{2}($| [0-2][0-9][0-5][0-9]$)";
    private static final String UNHANDLED_EXCEPTION_STRING = "Error: unexpected uncaught exception in Parser";

    /**
     * Creates a user command object from the given input command string
     *
     * @param input the input command string
     * @return the user command object
     * @throws InvalidCommandException if the input command is invalid
     */
    private CommandAbstract createUserCommand(String input) throws InvalidCommandException {
        try { // Identify whitespace commands
            String command = input.split(" ")[0];
        } catch (IndexOutOfBoundsException indexOutOfBoundsExcept) {
            throw new InvalidCommandException("Please input a valid command.");
        }

        switch (input.split(" ")[0].toLowerCase()) {
        case "bye":
            return new ByeCommand(input);
        case "list":
            return new ListCommand(input);
        case "mark":
            return new MarkCommand(input);
        case "unmark":
            return new UnmarkCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "todo":
            return new CreateTodoCommand(input, false);
        case "deadline":
            return new CreateDeadlineCommand(input, false);
        case "event":
            return new CreateEventCommand(input, false);
        case "find":
            return new FindCommand(input);
        case "sort":
            return new SortCommand(input);
        default:
            // fall-through
        }
        throw new InvalidCommandException("Please input a valid command.");
    }

    /**
     * Processes the user command and executes it on the specified task list
     *
     * @param input the input command string
     * @param tasklist the task list to operate on
     * @return appropriate chatbot response, exception details or UNHANDLED_EXCEPTION_STRING if
     *     there are unhandled edge cases
     */
    public String passUserCommand(String input, TaskList tasklist) {
        assert(tasklist != null);
        try {
            CommandAbstract command = createUserCommand(input);
            return command.execute(tasklist);
        } catch (InvalidCommandException invalidCommandExcept) {
            return invalidCommandExcept.getBotMessage();
        } catch (Exception uncaughtExcept) {
            return UNHANDLED_EXCEPTION_STRING;
        }
    }

    /**
     * Creates a command object from saved memory to restore state of task list to the most recently memorised state
     *
     * @param input the input data string extracted from memory
     * @return the command object
     * @throws CorruptDataException if the input data is corrupt
     */
    private CommandAbstract createDataCommand(String input) throws CorruptDataException {
        Pattern dataRegex = Pattern.compile(GENERAL_DATA_REGEX_STRING);
        Pattern deadlineRegex = Pattern.compile(DEADLINE_REGEX_STRING);
        Pattern eventRegex = Pattern.compile(EVENT_REGEX_STRING);
        Matcher dataMatcher = dataRegex.matcher(input);

        if (dataMatcher.find()) {
            String[] delimited = input.split(" \\| ");
            boolean isDone = delimited[1].equals("1");
            dataMatcher.reset();
            switch(delimited[0]) {
            case "T":
                return new CreateTodoCommand("todo " + delimited[2], isDone);
            case "D":
                if (!dataMatcher.usePattern(deadlineRegex).find()) {
                    throw new CorruptDataException(input);
                }
                return new CreateDeadlineCommand("deadline " + delimited[2]
                        + " /by " + delimited[3], isDone);
            case "E":
                if (!dataMatcher.usePattern(eventRegex).find()) {
                    throw new CorruptDataException(input);
                }
                String[] dateAndTimeDelimited = delimited[3].split(" - ");
                return new CreateEventCommand("event " + delimited[2]
                        + " /from " + dateAndTimeDelimited[0] + " /to "
                        + dateAndTimeDelimited[1], isDone);
            default:
                // fall through
            }
        }
        throw new CorruptDataException(input);
    }

    /**
     * Processes the input from saved memory and executes it on the specified task list to restore most recently
     * memorised state
     *
     * @param input the input data string extracted from memory
     * @param tasklist the task list to operate on
     * @return appropriate chatbot response string, or UNHANDLED_EXCEPTION_STRING for any edge cases not caught
     * @throws CorruptDataException if the input data is corrupt
     */
    public String passDataCommand(String input, TaskList tasklist) throws CorruptDataException {
        try {
            CommandAbstract command = createDataCommand(input);
            return command.execute(tasklist);
        } catch (CorruptDataException corruptDataExcept) {
            throw corruptDataExcept;
        } catch (Exception uncaughtExcept) {
            return UNHANDLED_EXCEPTION_STRING;
        }
    }
}
