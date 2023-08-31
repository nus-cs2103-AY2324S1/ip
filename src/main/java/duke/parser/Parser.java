package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.IncorrectCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;

/**
 * Represents the Parser Class.
 * Responsible for parsing user input.
 *
 * @author Shishir
 */
public class Parser {

    /**
     * Returns a command based on user input.
     * @param fullCommand String representation of user input.
     * @return Command based on user input.
     */
    public static Command parse(String fullCommand) {

        String[] split = fullCommand.split(" ", 2);
        Command c = null;

        switch(split[0]) {
        case "bye":
            c = validateCommand(split, true);
            break;
        case "list":
            c = validateCommand(split, false);
            break;
        case "mark":
        case "unmark":
            c = validateIndex(split, true);
            break;
        case "todo":
        case "deadline":
        case "event":
            c = validateTask(split);
            break;
        case "delete":
            c = validateIndex(split, false);
            break;
        case "find":
            c = validateFind(split);
            break;
        default:
            c = new IncorrectCommand("I'm sorry, I couldn't understand that. Please try again!");
        }
        return c;
    }

    private static Command validateIndex(String[] split, boolean flag) {
        // Check if mark is receiving any input or receiving extra input
        if (split.length != 2 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid mark command!");
        }

        // Check if mark is not receiving a number.
        int index;
        try {
            index = Integer.parseInt(split[1]);
        } catch (NumberFormatException exp) {
            return new IncorrectCommand("I cannot mark a character! Please enter a number.");
        }

        // Check if index is greater than 0.
        if (index <= 0) {
            return new IncorrectCommand("Please enter a number greater than 0!");
        }

        return flag ? new MarkCommand(index, split[0]) : new DeleteCommand(index);
    }

    private static Command validateTask(String[] split) {

        if (split.length == 1 || split[1].isBlank()) {
            return new IncorrectCommand("Please enter a valid task.");
        }

        if (split[0].equals("deadline")) {

            if (!split[1].contains(" /by ")) {
                return new IncorrectCommand("Please enter a /by command to depict the deadline.");
            }

            String[] task = split[1].split(" /by ", 2);

            if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
                return new IncorrectCommand("Please enter a valid task and/or deadline.");
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
                return new AddCommand(task[0], LocalDateTime.parse(task[1], formatter));
            } catch (DateTimeParseException e) {
                return new IncorrectCommand("Please enter the date & time in a valid format! (DD/MM/YY HHMM)");
            }

        } else if (split[0].equals("event")) {

            if (!split[1].contains(" /from ")) {
                return new IncorrectCommand("Please enter a /from command to depict the deadline.");
            }

            String[] task = split[1].split(" /from ", 2);

            // Check if task entered is empty
            if (task.length <= 1 || task[1].isBlank() || task[0].isBlank()) {
                return new IncorrectCommand("Please enter a valid task.");
            }

            // Check if /to is present
            if (!task[1].contains(" /to ")) {
                return new IncorrectCommand("There is no /to command present. Please try again.");
            }

            String[] to = task[1].split(" /to ", 2);

            if (to.length <= 1 || to[1].isBlank() || to[0].isBlank()) {
                return new IncorrectCommand("Please enter valid to & from dates");
            }

            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
                LocalDateTime from = LocalDateTime.parse(to[0], formatter);
                LocalDateTime till = LocalDateTime.parse(to[1], formatter);
                if (from.isAfter(till) || from.isEqual(till)) {
                    return new IncorrectCommand("Please ensure that the date range is valid!");
                }
                return new AddCommand(task[0], from, till);
            } catch (DateTimeParseException e) {
                return new IncorrectCommand("Please enter the date & time in a valid format! (DD/MM/YY HHMM)");
            }

        } else {
            return new AddCommand(split[1]);
        }
    }

    private static Command validateCommand(String[] split, boolean flag) {
        if (split.length != 1) {
            return new IncorrectCommand("Please enter a valid command!");
        }
        return flag ? new ExitCommand() : new ListCommand();
    }


    private static Command validateFind(String[] split) {
        if (split.length <= 1) {
            return new IncorrectCommand("Please enter a valid command!");
        }
        return new FindCommand(split[1]);
    }

}
