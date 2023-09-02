package duke.main;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidSyntaxException;
import duke.task.*;

import java.time.DateTimeException;

/**
 * A class handling commands from user input
 */
public class Parser {

    /**
     * A list of valid commands
     */
    public enum ValidCommand {
        BYE, LIST, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, UNKNOWN
    };

    /**
     * The current command from user input
     */
    private ValidCommand currentCommand;

    /**
     * Returns Command object based on user input.
     * @param inp The String array containing the user input
     * @param taskList The list of tasks under the user
     * @return Command
     * @throws DukeException if the format of Command is wrong
     */
    public Command parse(String[] inp, TaskList taskList) throws DukeException {
        String title = "";
        String startDate = "";
        String endDate = "";
        int taskIndex = 0;
        boolean isValid = false;
        if (!isValidCommand(inp[0])) {
            currentCommand = ValidCommand.UNKNOWN;
        } else {
            currentCommand = ValidCommand.valueOf(inp[0].toUpperCase());
            if (currentCommand == ValidCommand.MARK || currentCommand == ValidCommand.UNMARK ||
                    currentCommand == ValidCommand.DELETE) {
                if (inp.length == 2) {
                    try {
                        taskIndex = Integer.parseInt(inp[1]);
                    } catch (NumberFormatException e) {
                        currentCommand = ValidCommand.UNKNOWN;
                    }
                    ;
                } else {
                    currentCommand = ValidCommand.UNKNOWN;
                }
            } else if (currentCommand == ValidCommand.TODO || currentCommand == ValidCommand.DEADLINE) {
                int i = 1;
                title = "";
                endDate = "";
                for (; i < inp.length; i++) {
                    if (inp[i].equals("/by")) break;
                    if (i == 1) {
                        isValid = true;
                    }
                    if (title.equals("")) {
                        title = inp[i];
                    } else {
                        title += " " + inp[i];
                    }
                }
                for (int j = i + 1; j < inp.length; j++) {
                    if (endDate.equals("")) {
                        endDate = inp[j];
                    } else {
                        endDate += " " + inp[j];
                    }
                }
            } else if (currentCommand == ValidCommand.EVENT) {
                int start = 1;
                int end = 0;
                title = "";
                startDate = "";
                endDate = "";
                for (; start < inp.length; start++) {
                    if (inp[start].equals("/from")) break;
                    if (start == 1) {
                        isValid = true;
                    }
                    if (title.equals("")) {
                        title = inp[start];
                    } else {
                        title += " " + inp[start];
                    }
                }

                for (end = start + 1; end < inp.length; end++) {
                    if (inp[end].equals("/to")) break;
                    if (startDate.equals("")) {
                        startDate = inp[end];
                    } else {
                        startDate += " " + inp[end];
                    }
                }

                for (int j = end + 1; j < inp.length; j++) {
                    if (endDate.equals("")) {
                        endDate = inp[j];
                    } else {
                        endDate += " " + inp[j];
                    }
                }
            }
        }

        switch (currentCommand) {
            case UNKNOWN:
                if (isValidCommand(inp[0])) {
                    throw new InvalidSyntaxException("The format of the command is invalid.");
                } else {
                    throw new InvalidSyntaxException("I'm sorry, but I don't know what that means :-(");
                }
            case BYE:
                return new ByeCommand();
            case LIST:
                return new ListCommand();
            case UNMARK:
                if (taskIndex > taskList.size() || taskIndex < 1) {
                    throw new InvalidSyntaxException("The task does not exist.");
                } else {
                    return new UnmarkCommand(taskIndex);
                }
            case MARK:
                if (taskIndex > taskList.size() || taskIndex < 1) {
                    throw new InvalidSyntaxException("The task does not exist.");
                } else {
                    return new MarkCommand(taskIndex);
                }
            case DELETE:
                if (taskIndex > taskList.size() || taskIndex < 1) {
                    throw new InvalidSyntaxException("The task does not exist.");
                } else {
                    return new DeleteCommand(taskIndex);
                }
            default:
                if (inp.length < 2 || !isValid) {
                    throw new InvalidSyntaxException("The description of a " + inp[0] + " cannot be empty.");
                }
                if (inp[0].equals("todo")) {
                    return new AddCommand("T", title, "", "");
                }
                if (inp[0].equals("deadline")) {
                    if (endDate.equals("")) {
                        throw new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
                    }
                    return new AddCommand("D", title, "", endDate);
                }
                if (inp[0].equals("event")) {
                    if (startDate.equals("")) {
                        throw new InvalidSyntaxException("The start date of a " + inp[0] + " cannot be empty.");
                    }
                    if (endDate.equals("")) {
                        throw new InvalidSyntaxException("The end date of a " + inp[0] + " cannot be empty.");
                    }
                    return new AddCommand("E", title, startDate, endDate);
                }
        }
        return null;
    }

    /**
     * Returns true if user input is valid command, false otherwise
     * @param value The String containing command from user input
     * @return true or false
     */
    public static boolean isValidCommand(String value) {
        try {
            ValidCommand val = ValidCommand.valueOf(value.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
