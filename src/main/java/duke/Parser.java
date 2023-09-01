package duke;

import java.util.regex.Matcher;
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
    public static String getKeyString(String fullCommand) {
        return fullCommand.split(" ", 2)[1].trim();
    }

    /**
     * A method to parse command that user gave
     * @param fullCommand string that user gave
     * @return A Command object representing the command user gives
     * @throws DukeException when user input does not represent any particular
     * command
     */
    public static Command parse(String fullCommand) throws DukeException {
        int taskType = Parser.getCommand(fullCommand);
        switch (taskType) {
        case 0: {
            return new ListCommand();
        }
        case 1: {
            Matcher matcher = Pattern.compile("mark ").matcher(fullCommand);
            if (!matcher.find()) {
                // return error
            }
            int index = Parser.getIndex(fullCommand);
            return new MarkCommand(index - 1);
        }
        case 2: {
            Matcher matcher = Pattern.compile("unmark ").matcher(fullCommand);
            if (!matcher.find()) {
                // return error
            }
            int index = Parser.getIndex(fullCommand);
            return new UnmarkCommand(index - 1);
        }
        case 3: {
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
            }
        }
        case 4: {
            Matcher matcher = Pattern.compile("delete ").matcher(fullCommand);
            if (!matcher.find()) {
                // return error
            }
            int index = Parser.getIndex(fullCommand);
            return new DeleteCommand(index - 1);
        }
        case 5: {
            Matcher matcher = Pattern.compile("find ").matcher(fullCommand);
            if (!matcher.find()) {
                // return error
            }
            String keyString = Parser.getKeyString(fullCommand);
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

}
