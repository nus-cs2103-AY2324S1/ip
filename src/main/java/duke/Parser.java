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

/*
 * A class that deals with making sense of the user commands
 */

public class Parser {

    public Parser() {

    }

    /*
     * A method that returns an integer which represents the type of command user
     * inputted.
     * 
     * @params userInput string that user gave
     * 
     * @return integer based on the type of command user gave
     */
    public static int getCommand(String userInput) {
        userInput = userInput.trim();
        boolean isList = userInput.equals("list");
        boolean isMark = Pattern.compile("^mark").matcher(userInput).find();
        boolean isUnmark = Pattern.compile("^unmark").matcher(userInput).find();
        boolean isTodo = Pattern.compile("^todo").matcher(userInput).find();
        boolean isDeadline = Pattern.compile("^deadline").matcher(userInput).find();
        boolean isEvent = Pattern.compile("^event").matcher(userInput).find();
        boolean isDelete = Pattern.compile("^delete").matcher(userInput).find();
        boolean isExit = Pattern.compile("^bye").matcher(userInput).find();
        boolean isFind = Pattern.compile("^find").matcher(userInput).find();
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

    /*
     * A method that returns an integer based on the type of task user wants to add
     * 
     * @params userInput string that user gave
     * 
     * @return integer based on the type of task user wants to add
     */
    public static int getTaskType(String userInput) {
        boolean isTodo = Pattern.compile("^todo").matcher(userInput).find();
        boolean isDeadline = Pattern.compile("^deadline").matcher(userInput).find();
        // boolean isEvent = Pattern.compile("^event").matcher(userInput).find();
        return isTodo ? 0 : isDeadline ? 1 : 2;
    }

    /*
     * A method that gets the index of a task that user specified in command, if any
     * 
     * @params userInput string that user gave
     * 
     * @return index that user specified
     */

    // how to check if string is parseable without parsing it?
    public static int getIndex(String userInput) {
        return Integer.parseInt(userInput.split(" ")[1]);
    }

    public static String getKeyString(String userInput) {
        return userInput.split(" ", 2)[1].trim();
    }

    /*
     * A method to parse command that user gave
     * 
     * @params fullCommand string that user gave
     * 
     * @return A Command object representing the command user gives
     * 
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
