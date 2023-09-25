package parser;

import commands.ArchiveCommand;
import commands.ByeCommand;
import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ScheduleCommand;
import commands.TodoCommand;
import commands.UnknownCommand;
import commands.UnmarkCommand;
import data.Save;
import duke.DukeException;

/**
 * Represents a parser that interprets user inputs into commands for the Duke chatbot.
 * Provides methods for splitting and interpreting the input string to determine the
 * appropriate command to execute.
 */
public class Parser {
    private final Save savior;

    public Parser(Save savior) {
        this.savior = savior;
    }

    /**
     * Converts the user input into a corresponding Command object based on the given input string.
     *
     * @param input The input string.
     * @return The Command object based on the interpreted input.
     * @throws DukeException If the input command format is incorrect or unrecognized.
     */
    public Command issueCommand(String input) throws DukeException {
        assert input != null;
        String[] inputParts = splitInput(input);
        assert inputParts.length > 0;
        String command = inputParts[0].toLowerCase();
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            if (inputParts.length < 2) {
                throw new DukeException(" Provide the task number.");
            }
            int markTaskNumber;
            try {
                markTaskNumber = Integer.parseInt(splitInput(inputParts[1])[0]) - 1;
            } catch (NumberFormatException invalidInt) {
                throw new DukeException(" Hmmm, your input format for marking tasks is incorrect;\n"
                        + " Format: mark [int task number > 0]");
            }
            return new MarkCommand(markTaskNumber);
        case "unmark":
            if (inputParts.length < 2) {
                throw new DukeException(" Provide the task number.");
            }
            int unmarkTaskNumber;
            try {
                unmarkTaskNumber = Integer.parseInt(splitInput(inputParts[1])[0]) - 1;
            } catch (NumberFormatException invalidInt) {
                throw new DukeException(" Hmmm, your input format for unmarking tasks is incorrect;\n"
                        + " Format: unmark [int task number > 0]");
            }
            return new UnmarkCommand(unmarkTaskNumber);
        case "todo":
            if (inputParts.length > 1) {
                return new TodoCommand(inputParts[1]);
            } else {
                return new TodoCommand("");
            }
        case "deadline":
            if (inputParts.length > 1) {
                return new DeadlineCommand(inputParts[1], this);
            } else {
                return new DeadlineCommand("", this);
            }
        case "event":
            if (inputParts.length > 1) {
                return new EventCommand(inputParts[1], this);
            } else {
                return new EventCommand("", this);
            }
        case "delete":
            if (inputParts.length < 2) {
                throw new DukeException(" Provide a task number. "
                        + "Format: delete [int task number > 0]");
            }
            int deleteTaskNumber;
            try {
                deleteTaskNumber = Integer.parseInt(splitInput(inputParts[1])[0]) - 1;
            } catch (NumberFormatException invalidInt) {
                throw new DukeException(" Hmmm, your input format for deleting tasks is incorrect;\n"
                        + " Format: delete [int task number > 0]");
            }
            return new DeleteCommand(deleteTaskNumber);
        case "schedule":
            if (inputParts.length < 2 || !inputParts[1].contains("/on")) {
                throw new DukeException(" Provide the date in the format:\n"
                        + " schedule /on dd/MM/yyyy");
            }
            String dateInput = splitByKeyword(inputParts[1], "/on")[1].trim() + " 0000";
            return new ScheduleCommand(dateInput);
        case "find":
            if (inputParts.length < 2) {
                throw new DukeException(" Keyword. I NEED A KEYWORD!!! ");
            }
            return new FindCommand(inputParts[1]);
        case "archive":
            return new ArchiveCommand(savior);
        default:
            return new UnknownCommand(input);
        }
    }

    /**
     * Splits the input string into two parts based on the first space encountered.
     *
     * @param input The input string.
     * @return An array with two strings: the command type and the command details.
     */
    public String[] splitInput(String input) {
        return input.split(" ", 2);
    }

    /**
     * Splits the input string by the specified keyword.
     *
     * @param input The input string.
     * @param keyword The keyword used to split the input.
     * @return An array containing the portions of the input separated by the keyword.
     */
    public String[] splitByKeyword(String input, String keyword) {
        return input.split(keyword, 2);
    }
}
