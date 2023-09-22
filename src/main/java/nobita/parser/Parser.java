package nobita.parser;

import nobita.command.AddCommand;
import nobita.command.Command;
import nobita.command.DeleteCommand;
import nobita.command.ExitCommand;
import nobita.command.FindCommand;
import nobita.command.ListCommand;
import nobita.command.MarkCommand;
import nobita.command.UnmarkCommand;
import nobita.command.UpdateCommand;
import nobita.exception.NobitaException;

import java.time.LocalDate;

/**
 * Class that encapsulates Parser.
 * Parser that is used to read in and understand user commands.
 *
 * @author Zheng Chenglong
 */
public class Parser {

    /**
     * Parses the line of command given by the user.
     *
     * @param fullCommand A line of user input command string to be parsed.
     * @return A Command instruction that the user specify.
     * @throws NobitaException If command received are of wrong format.
     */
    public static Command parse(String fullCommand)  throws NobitaException {
        String[] commands = fullCommand.split(" ", 2);
        String commandType = commands[0].toLowerCase();
        switch (commandType) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(checkNumber(commands[1]));
        case "unmark":
            return new UnmarkCommand(checkNumber(commands[1]));
        case "todo":
            if (commands.length < 2) {
                throw new NobitaException("The description of a todo cannot be empty.\n"
                        + "Please specify a description.");
            }
            return new AddCommand(commands[1]);
        case "deadline":
            if (commands.length < 2) {
                throw new NobitaException("The description of a deadline cannot be empty.\n"
                        + "Please specify a description.");
            }
            String[] deadlineFields = commands[1].split(" /by ");
            if (deadlineFields.length < 2) {
                throw new NobitaException("The /by date of a deadline cannot be empty.\n"
                        + "Please specify a /by date.");
            }
            try {
                LocalDate.parse(deadlineFields[1]);
            } catch (Exception e) {
                throw new NobitaException("Date format should be in yyyy-mm-dd");
            }
            return new AddCommand(deadlineFields[0], deadlineFields[1]);
        case "event":
            if (commands.length < 2) {
                throw new NobitaException("The description of a event cannot be empty.\n"
                        + "Please specify a description.");
            }
            String[] eventFields = commands[1].split(" /from ");
            if (eventFields.length < 2) {
                throw new NobitaException("The /from date of a event cannot be empty.\n"
                        + "Please specify a /from date.");
            }
            String[] fromAndTo = eventFields[1].split(" /to ");
            if (fromAndTo.length < 2) {
                throw new NobitaException("The /to date of a event cannot be empty.\n"
                        + "Please specify a /to date.");
            }
            try {
                LocalDate.parse(fromAndTo[0]);
                LocalDate.parse(fromAndTo[1]);
            } catch (Exception e) {
                throw new NobitaException("Date format should be in yyyy-mm-dd");
            }
            return new AddCommand(eventFields[0], fromAndTo[0], fromAndTo[1]);
        case "delete":
            return new DeleteCommand(Integer.parseInt(commands[1]) - 1);
        case "find":
            return new FindCommand(commands[1]);
        case "update":
            String[] updateTaskAndField = commands[1].split(" ");
            return new UpdateCommand(Integer.parseInt(updateTaskAndField[0]) - 1, updateTaskAndField[1], updateTaskAndField[2]);
        default:
            throw new NobitaException("I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Check if the task number passed in is a number.
     *
     * @param toTest The number parameter that is pass in.
     * @return An integer representing the correct task index.
     * @throws NobitaException If parameter receive is not a number.
     */
    private static int checkNumber(String toTest) throws NobitaException {
        try {
            return Integer.parseInt(toTest) - 1;
        } catch (NumberFormatException e) {
            throw new NobitaException("Only numbers are allow");
        }
    }
}
