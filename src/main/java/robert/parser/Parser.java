package robert.parser;

import java.time.DateTimeException;
import java.time.LocalDate;

import robert.command.*;
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
        if (fullCommand.isEmpty()) {
            return new BlankCommand();
        }

        String[] splitCommand = fullCommand.split(" ", 2);

        String commandType = splitCommand[0];

        if (commandType.equals("list")) {
            if (splitCommand.length > 1) {
                throw new RobertException("Unnecessary parameters added.\n"
                        + "Type 'list' if you intend to list your tasks.");
            }

            return new ListCommand();
        }

        if (commandType.equals("bye")) {
            if (splitCommand.length > 1) {
                throw new RobertException("Additional parameters added.\n"
                        + "Did you intend to exit? If not, be careful!");
            }

            return new ExitCommand();
        }

        if (commandType.equals("mark")) {
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

        if (commandType.equals("unmark")) {
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

        if (commandType.equals("todo")) {
            if (splitCommand.length == 1) {
                throw new RobertException("The description of a todo cannot be empty.\n"
                        + "Please add a description to your todo task.");
            }

            return new AddCommand(splitCommand[1]);
        }

        if (commandType.equals("event")) {
            if (splitCommand.length == 1) {
                throw new RobertException("The description of an event cannot be empty.\n"
                        + "Please add a description to your event task.");
            }

            String parameters = splitCommand[1];
            if (!parameters.contains("/from") || !parameters.contains("/to")) {
                throw new RobertException("The event's start and/or end time is/are not specified properly.\n"
                        + "Please make sure '/from' and '/to' are properly indicated.");
            }

            String[] eventParameters = parameters.split(" /from ");
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

            try {
                LocalDate fromDate = LocalDate.parse(dateParameters[0]);
                LocalDate toDate = LocalDate.parse(dateParameters[1]);
                return new AddCommand(eventDescription, fromDate, toDate);
            } catch (DateTimeException e) {
                throw new RobertException("Date provided does not match format.\n"
                        + "Please write your date in the format of 'YYYY-MM-DD'.");
            }
        }

        if (commandType.equals("deadline")) {
            if (splitCommand.length == 1) {
                throw new RobertException("The description of a deadline cannot be empty.\n"
                        + "Please add a description to your deadline task.");
            }

            String parameters = splitCommand[1];
            if (!parameters.contains("/by")) {
                throw new RobertException("The deadline's due date is not specified properly.\n"
                        + "Please make sure '/by' is properly indicated.");
            }

            String[] deadlineParameters = parameters.split(" /by ");

            if (deadlineParameters.length < 2) {
                throw new RobertException("There are parameters missing.\n"
                        + "Please make sure you have both the task description and the due date entered.");
            }

            String deadlineDescription = deadlineParameters[0];

            try {
                LocalDate byDate = LocalDate.parse(deadlineParameters[1]);
                return new AddCommand(deadlineDescription, byDate);

            } catch (DateTimeException e) {
                throw new RobertException("Date provided does not match format.\n"
                        + "Please write your date in the format of 'YYYY-MM-DD'.");
            }
        }

        if (commandType.equals("delete")) {
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

        if (commandType.equals("clear")) {
            return new ClearCommand();
        }

        if (commandType.equals("filter")) {
            if (splitCommand.length == 1) {
                throw new RobertException("The date of the tasks is not indicated.\n"
                        + "Please add a date in the format 'YYYY-MM-DD'.");
            }

            try {
                LocalDate date = LocalDate.parse(splitCommand[1]);
                return new FilterCommand(date);

            } catch (DateTimeException e) {
                throw new RobertException("Date provided does not match format.\n"
                        + "Please write your date in the format of 'YYYY-MM-DD'.");
            }
        }

        if (commandType.equals("find")) {
            if (splitCommand.length == 1) {
                throw new RobertException("The keyword is not indicated.\n"
                        + "Please add a keyword so that I can find relevant tasks.");
            }

            return new FindCommand(splitCommand[1]);
        }

        throw new RobertException("I'm sorry, but I don't know what that command means :(");
    }
}
