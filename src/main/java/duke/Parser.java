package duke;

import java.util.regex.Pattern;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.exception.DukeException;
import duke.exception.UnknownTaskTypeException;

/**
 * A class that deals with making sense of the user commands
 */
public class Parser {

    /**
     * A method that returns an integer which represents the type of command user
     * inputted.
     * @param fullCommand string that user gave
     * @return integer based on the type of command user gave
     */
    public static int getCommand(String fullCommand) {
        fullCommand = fullCommand.trim();
        boolean isList = fullCommand.equals("list");
        boolean isMark = Pattern.compile("^mark").matcher(fullCommand).find();
        boolean isUnmark = Pattern.compile("^unmark").matcher(fullCommand).find();
        boolean isTodo = Pattern.compile("^todo").matcher(fullCommand).find();
        boolean isDeadline = Pattern.compile("^deadline").matcher(fullCommand).find();
        boolean isEvent = Pattern.compile("^event").matcher(fullCommand).find();
        boolean isDelete = Pattern.compile("^delete").matcher(fullCommand).find();
        boolean isExit = Pattern.compile("^bye").matcher(fullCommand).find();
        boolean isFind = Pattern.compile("^find").matcher(fullCommand).find();
        boolean isValidTask = isTodo || isDeadline || isEvent;

        return isList
                ? 0
                : isMark
                ? 1
                : isUnmark
                ? 2
                : isValidTask
                ? 3
                : isDelete
                ? 4
                : isFind
                ? 5
                : isExit
                ? 6
                : 7;
    }

    /**
     * A method that returns an integer based on the type of task user wants to add
     * @param fullCommand string that user gave
     * @return integer based on the type of task user wants to add
     */
    public static int getTaskType(String fullCommand) {
        boolean isTodo = Pattern.compile("^todo").matcher(fullCommand).find();
        boolean isDeadline = Pattern.compile("^deadline").matcher(fullCommand).find();
        // boolean isEvent = Pattern.compile("^event").matcher(fullCommand).find();
        return isTodo ? 0 : isDeadline ? 1 : 2;
    }

    /**
     * A method that gets the index of a task that user specified in command, if any
     * @param fullCommand string that user gave
     * @return index that user specified
     */
    public static int getIndex(String fullCommand) {
        // how to check if string is parseable without parsing it?
        return Integer.parseInt(fullCommand.split(" ")[1]);
    }

    /**
     * A method that returns the keyword that user is searching for when user uses the find command
     * @param fullCommand string that user gave
     * @return string that user wants to search with regards to tasks' descriptions
     */
    public static String getString(String fullCommand) {
        return fullCommand.split(" ", 2)[1].trim();
    }

    /**
     * A method to parse command that user gave
     * @param fullCommand string that user gave
     * @return A Command object representing the command user gives
     * @throws DukeException when user input does not represent any command
     */
    public static Command parse(String fullCommand) throws DukeException {
        int taskType = Parser.getCommand(fullCommand);
        switch (taskType) {
        case 0: {
            return new ListCommand();
        }
        case 1: {
            int index = indexForCommand("mark ", fullCommand);
            return new MarkCommand(index - 1);
        }
        case 2: {
            int index = indexForCommand("unmark ", fullCommand);
            return new UnmarkCommand(index - 1);
        }
        case 3: {
            return parseTaskAdditionCommand(fullCommand);
        }
        case 4: {
            int index = indexForCommand("delete ", fullCommand);
            return new DeleteCommand(index - 1);
        }
        case 5: {
            String keyString = stringForCommand("find ", fullCommand);
            return new FindCommand(keyString);
        }
        case 6: {
            return new ExitCommand();
        }
        default: {
            throw new UnknownTaskTypeException();
        }
        }
    }

    /**
     * A method that returns index for commands that specifies index.
     * @param regexExpression regex representing command type.
     * @param fullCommand string representing user input.
     * @return index as specified by user in user input.
     */
    public static int indexForCommand(String regexExpression, String fullCommand) {
        Pattern.compile(regexExpression).matcher(fullCommand).find();
        return Parser.getIndex(fullCommand);
    }

    /**
     * A method that returns string for commands that specifies string.
     * @param regexExpression regex representing command type.
     * @param fullCommand string representing user input.
     * @return string as specified by user in user input.
     */
    public static String stringForCommand(String regexExpression, String fullCommand) {
        Pattern.compile(regexExpression).matcher(fullCommand).find();
        return Parser.getString(fullCommand);
    }

    /**
     * A method that returns commands related to task addition.
     * @param fullCommand string representing user input.
     * @return command to add task for tasks of Todo, Deadline and Event class.
     * @throws DukeException
     */
    public static Command parseTaskAdditionCommand(String fullCommand) throws DukeException {
        switch (Parser.getTaskType(fullCommand)) {
            case 0: {
                return new AddCommand().new TodoCommand(fullCommand);
            }
            case 1: {
                return new AddCommand().new DeadlineCommand(fullCommand);
            }
            case 2: {
                return new AddCommand().new EventCommand(fullCommand);
            }
            default: {
                throw new UnknownTaskTypeException();
            }
            }
    }

}
