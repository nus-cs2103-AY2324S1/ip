package barbie;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import barbie.commands.Command;
import barbie.commands.DeadlineCommand;
import barbie.commands.DeleteCommand;
import barbie.commands.ExitCommand;
import barbie.commands.FindCommand;
import barbie.commands.ListCommand;
import barbie.commands.MarkCommand;
import barbie.commands.PartyCommand;
import barbie.commands.TodoCommand;
import barbie.commands.UnmarkCommand;
import barbie.exceptions.BarbieDateTimeFormatException;
import barbie.exceptions.BarbieException;
import barbie.exceptions.BarbieNoDeadlineException;
import barbie.exceptions.BarbieNoDescException;
import barbie.exceptions.BarbieNoKeywordException;
import barbie.exceptions.BarbieNoSuchCommandException;
import barbie.exceptions.BarbieNoTimingException;
import barbie.exceptions.BarbieTaskNumberException;

/**
 * Represents a class which parses input.
 */
public class Parser {

    /**
     * Constructor for the Parser class.
     */
    public Parser() {
    }

    /**
     * Parse input given to return the command used.
     * @param input text input of the user
     * @return command to be executed based on the text parsed
     * @throws BarbieException thrown in the commands
     */
    public Command parse(String input) throws BarbieException {
        try {
            String[] parts = input.split(" ", 2);
            String inputCommand = parts[0].toLowerCase();

            switch (inputCommand) {
            case "mark":
            case "unmark":
            case "del":
                return parseEditTasks(parts);

            case "todo":
            case "deadline":
            case "party":
                return parseAddTasks(parts);

            case "list": return new ListCommand();
            case "find": return parseFindTask(parts);
            case "bye": return new ExitCommand();
            default:
                throw new BarbieNoSuchCommandException();
            }

        } catch (NumberFormatException e) {
            throw new BarbieTaskNumberException();
        } catch (DateTimeParseException e) {
            throw new BarbieDateTimeFormatException();
        }


    }

    /**
     * Abstracts the logic of parsing the commands that add tasks.
     * For example: todo, deadline, party
     * @param inputArr input after being split into an array
     * @return Command to be executed
     * @throws BarbieException exception when the formatting of the input is wrong
     */
    private Command parseAddTasks(String[] inputArr) throws BarbieException {
        if (inputArr.length < 2) {
            throw new BarbieNoDescException();
        }
        String[] parts2 = inputArr[1].split("/");
        String desc;

        switch (inputArr[0].toLowerCase()) {
        case "deadline":
            if (parts2.length < 2) {
                throw new BarbieNoDeadlineException();
            }
            desc = parts2[0];
            LocalDate by = LocalDate.parse(parts2[1].strip());
            return new DeadlineCommand(desc, by);


        case "party":
            if (parts2.length < 3) {
                throw new BarbieNoTimingException();
            }
            desc = parts2[0];
            LocalDate from = LocalDate.parse(parts2[1].strip());
            LocalDate to = LocalDate.parse(parts2[2].strip());
            return new PartyCommand(desc, from, to);

        default:
            desc = inputArr[1];
            return new TodoCommand(desc);
        }

    }

    /**
     * Abstracts the logic of parsing the commands that edit tasks.
     * For example: mark, unmark, del
     * @param inputArr input after it has been split into an array
     * @return Command to be executed
     * @throws BarbieException exception when there is no task number
     */
    private Command parseEditTasks(String[] inputArr) throws BarbieException {
        if (inputArr.length < 2) {
            throw new BarbieTaskNumberException();
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(inputArr[1]) - 1;
            switch(inputArr[0].toLowerCase()) {
            case "mark": return new MarkCommand(taskNumber);
            case "unmark": return new UnmarkCommand(taskNumber);
            default: return new DeleteCommand(taskNumber);
            }
        } catch (NumberFormatException e) {
            throw new BarbieTaskNumberException();
        }




    }

    /**
     * Abstracts the logic of parsing the find function.
     * @param inputArr input after it has been split into an array
     * @return Command to be executed
     * @throws BarbieException exceptions that arise from no keyword
     */
    private Command parseFindTask(String[] inputArr) throws BarbieException {
        if (inputArr.length < 2) {
            throw new BarbieNoKeywordException();
        }
        String keyword = inputArr[1];
        return new FindCommand(keyword);
    }
}
