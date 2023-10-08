package robert.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import robert.command.AddCommand;
import robert.command.BlankCommand;
import robert.command.ClearCommand;
import robert.command.Command;
import robert.command.DeleteCommand;
import robert.command.ExitCommand;
import robert.command.FilterCommand;
import robert.command.FindCommand;
import robert.command.ListCommand;
import robert.command.MarkCommand;
import robert.command.UnmarkCommand;
import robert.exception.RobertException;

/**
 * A parser that is used to read in and manipulate user commands.
 *
 * @author Lee Zhan Peng
 */
public class Parser {

    /**
     * Parses the line of command given by the user.
     *
     * @param fullCommand the line of command to be parsed.
     * @return Command associated to the line of command given by the user.
     * @throws RobertException if the line of command is of wrong format.
     */
    public static Command parse(String fullCommand) throws RobertException {
        String[] splitCommand = fullCommand.split(" ", 2);
        switch (splitCommand[0]) {
        case "":
            return new BlankCommand();
        case "list":
            return parseListCommand(splitCommand);
        case "bye":
            return parseExitCommand(splitCommand);
        case "mark":
            return parseMarkCommand(splitCommand);
        case "unmark":
            return parseUnmarkCommand(splitCommand);
        case "todo":
            return parseTodoCommand(splitCommand);
        case "event":
            return parseEventCommand(splitCommand);
        case "deadline":
            return parseDeadlineCommand(splitCommand);
        case "delete":
            return parseDeleteCommand(splitCommand);
        case "clear":
            return new ClearCommand();
        case "filter":
            return parseFilterCommand(splitCommand);
        case "find":
            return parseFindCommand(splitCommand);
        default:
            throw new RobertException("I'm sorry, but I don't know what that command means :(");
        }
    }

    /**
     * Parses list command.
     *
     * @param splitCommand the list command that is to be parsed.
     * @return ListCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static ListCommand parseListCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length > 1) {
            throw new RobertException("Unnecessary parameters added.\n"
                    + "Type 'list' if you intend to list your tasks.");
        }
        return new ListCommand();
    }

    /**
     * Parses exit command.
     *
     * @param splitCommand the exit command that is to be parsed.
     * @return ExitCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static ExitCommand parseExitCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length > 1) {
            throw new RobertException("Additional parameters added.\n"
                    + "Did you intend to exit? If not, be careful!");
        }
        return new ExitCommand();
    }

    /**
     * Parses mark command.
     *
     * @param splitCommand the mark command that is to be parsed.
     * @return MarkCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static MarkCommand parseMarkCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The index used to mark a task cannot be empty.\n"
                    + "Please add an index.");
        }

        try {
            int markIndex = Integer.parseInt(splitCommand[1]) - 1;
            return new MarkCommand(markIndex);
        } catch (NumberFormatException e) {
            throw new RobertException("Cannot convert given index as integer.\n"
                    + "Please use proper integer as the index!");
        }
    }

    /**
     * Parses unmark command.
     *
     * @param splitCommand the unmark command that is to be parsed.
     * @return UnmarkCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static UnmarkCommand parseUnmarkCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The index used to unmark a task cannot be empty.\n"
                    + "Please add an index.");
        }

        try {
            int unmarkIndex = Integer.parseInt(splitCommand[1]) - 1;
            return new UnmarkCommand(unmarkIndex);
        } catch (NumberFormatException e) {
            throw new RobertException("Cannot convert given index as integer.\n"
                    + "Please use proper integer as the index!");
        }
    }

    /**
     * Parses todo command.
     *
     * @param splitCommand the todo command that is to be parsed.
     * @return AddCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static AddCommand parseTodoCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The description of a todo cannot be empty.\n"
                    + "Please add a description to your todo task.");
        }
        return new AddCommand(splitCommand[1]);
    }

    /**
     * Parses event command.
     *
     * @param splitCommand the event command that is to be parsed.
     * @return AddCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static AddCommand parseEventCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The description of an event cannot be empty.\n"
                    + "Please add a description to your event task.");
        }

        if (!splitCommand[1].contains("/from") || !splitCommand[1].contains("/to")) {
            throw new RobertException("The event's start and/or end time is/are not specified properly.\n"
                    + "Please make sure '/from' and '/to' are properly indicated.");
        }

        String[] eventParameters = splitCommand[1].split(" /from ");
        if (eventParameters.length < 2) {
            throw new RobertException("There are parameters missing.\n"
                    + "Please make sure you have both the task description and the due date entered.");
        }

        String eventDescription = eventParameters[0];
        String[] dateParameters = eventParameters[1].split(" /to ");
        if (dateParameters.length < 2) {
            throw new RobertException("There are parameters missing.\n"
                    + "Please make sure you have both the task description and the due date entered.");
        }

        LocalDate[] parsedDates = parseDates(dateParameters[0], dateParameters[1]);
        return new AddCommand(eventDescription, parsedDates[0], parsedDates[1]);
    }

    /**
     * Parses deadline command.
     *
     * @param splitCommand the deadline command that is to be parsed.
     * @return AddCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static AddCommand parseDeadlineCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The description of a deadline cannot be empty.\n"
                    + "Please add a description to your deadline task.");
        }

        String undividedDeadlineParameters = splitCommand[1];
        if (!undividedDeadlineParameters.contains("/by")) {
            throw new RobertException("The deadline's due date is not specified properly.\n"
                    + "Please make sure '/by' is properly indicated.");
        }

        String[] deadlineParameters = undividedDeadlineParameters.split(" /by ");
        if (deadlineParameters.length < 2) {
            throw new RobertException("There are parameters missing.\n"
                    + "Please make sure you have both the task description and the due date entered.");
        }

        String deadlineDescription = deadlineParameters[0];
        LocalDate[] parsedDates = parseDates(deadlineParameters[1]);
        return new AddCommand(deadlineDescription, parsedDates[0]);
    }

    /**
     * Parses delete command.
     *
     * @param splitCommand the delete command that is to be parsed.
     * @return DeleteCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static DeleteCommand parseDeleteCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The index used to delete a task cannot be empty.\n"
                    + "Please add an index.");
        }

        try {
            int deleteIndex = Integer.parseInt(splitCommand[1]) - 1;
            return new DeleteCommand(deleteIndex);
        } catch (NumberFormatException e) {
            throw new RobertException("Cannot convert given index as integer.\n"
                    + "Please use proper integer as the index!");
        }
    }

    /**
     * Parses filter command.
     *
     * @param splitCommand the filter command that is to be parsed.
     * @return FilterCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static FilterCommand parseFilterCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The date of the tasks is not indicated.\n"
                    + "Please add a date in the format 'YYYY-MM-DD'.");
        }

        try {
            LocalDate date = parseDates(splitCommand[1])[0];
            return new FilterCommand(date);
        } catch (DateTimeException e) {
            throw new RobertException("Your date seems to be incorrect.\n"
                    + "Please write your date in the format of 'YYYY-MM-DD'.\n"
                    + "If it is in the correct format, make sure the correct "
                    + "values are written.");
        }
    }

    /**
     * Parses find command.
     *
     * @param splitCommand the find command that is to be parsed.
     * @return FindCommand.
     * @throws RobertException if the line of command is of wrong format.
     */
    private static FindCommand parseFindCommand(String[] splitCommand) throws RobertException {
        if (splitCommand.length == 1) {
            throw new RobertException("The keyword is not indicated.\n"
                    + "Please add a keyword so that I can find relevant tasks.");
        }
        return new FindCommand(splitCommand[1]);
    }

    /**
     * Parses given dates from String to LocalDate.
     *
     * @param stringDate a varargs of dates to be converted to LocalDate.
     * @return An array of parsed LocalDates.
     * @throws RobertException if the dates are of incorrect format.
     */
    private static LocalDate[] parseDates(String ... stringDate) throws RobertException {
        LocalDate[] parsedDates = new LocalDate[stringDate.length];
        for (int i = 0; i < stringDate.length; i++) {
            try {
                LocalDate date = LocalDate.parse(stringDate[i]);
                parsedDates[i] = date;
            } catch (DateTimeException e) {
                throw new RobertException("Your date seems to be incorrect.\n"
                        + "Please write your date in the format of 'YYYY-MM-DD'.\n"
                        + "If it is in the correct format, make sure the correct "
                        + "values are written.");
            }
        }
        return parsedDates;
    }
}
