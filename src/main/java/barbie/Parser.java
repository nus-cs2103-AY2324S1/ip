package barbie;

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


import barbie.exceptions.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

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

            switch (parts[0].toLowerCase()) {
            case "mark":
            case "unmark":
            case "del":

                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(parts[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new BarbieTaskNumberException();
                }

                switch (inputCommand) {

                case "mark": return new MarkCommand(taskNumber);
                case "unmark": return new UnmarkCommand(taskNumber);
                case "del": return new DeleteCommand(taskNumber);

                }

            case "todo":
            case "deadline":
            case "party":
                if (parts.length < 2) {
                    throw new BarbieNoDescException();
                }
                String[] parts2 = parts[1].split("/");
                String desc;
                switch (inputCommand) {
                case "deadline":
                    if (parts2.length < 2) {
                        throw new BarbieNoDeadlineException();
                    }
                    desc = parts2[0];
                    LocalDate by = LocalDate.parse(parts2[1]);
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
                    desc = parts[1];
                    return new TodoCommand(desc);
                }

            case "list": return new ListCommand();

            case "find":
                if (parts.length < 2) {
                    throw new BarbieNoKeywordException();
                }
                String keyword = parts[1];
                return new FindCommand(keyword);
            case "bye":
                return new ExitCommand();
            default:
                throw new BarbieNoSuchCommandException();
            }

        } catch (NumberFormatException e) {
            throw new BarbieTaskNumberException();
        } catch (DateTimeParseException e) {
            throw new BarbieDateTimeFormatException();
        }


    }
}
