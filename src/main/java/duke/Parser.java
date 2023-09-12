package duke;

import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exceptions.WrongMarkException;
import duke.exceptions.UnknownCommandException;

/**
 * Describes the actions taken by the bot when commands is given
 */
public class Parser {
    /**
     * Describes the action taken
     * 
     * @param command  User command
     * @param taskList Takes in a taskList to add/delete/change details
     *                 in the taskList from done to not done and vice
     *                 versa
     * @param helper   Takes in the bot interaction with the user
     * @param storage  Takes in the storage to store the taskList
     *                 of the particular user after the user wants to end
     *                 the program of the bot
     * @return a boolean that allow the main application to end the program
     *         when a particular command "bye" is given from the user
     * @throws WrongMarkException      if asked to mark a marked file or unmark a
     *                                 file
     *                                 that is not marked
     * @throws UnknownCommandException if asked commands that the bot do not
     *                                 understand
     */
    public static Command parse(String command) throws WrongMarkException, UnknownCommandException {
        int taskNumber = 0;
        Command commandGiven = null;
        String[] splittedCommand = command.split(" ");
        String commandType = splittedCommand[0];
        switch (commandType) {
            case "bye":
                commandGiven = new ByeCommand();
                break;
            case "find":
                String keyword = splittedCommand[1];
                commandGiven = new FindCommand(keyword);
                break;
            case "list":
                commandGiven = new ListCommand();
                break;
            case "mark":
                taskNumber = Integer.parseInt(splittedCommand[1]);
                commandGiven = new MarkCommand(taskNumber);
                break;
            case "unmark":
                taskNumber = Integer.parseInt(splittedCommand[1]);
                commandGiven = new UnmarkCommand(taskNumber);
                break;
            case "delete":
                String[] splittedInput = command.split(" ");
                taskNumber = Integer.parseInt(splittedInput[1]);
                commandGiven = new DeleteCommand(taskNumber);
                break;
            default:
                commandGiven = new AddTaskCommand(command);
        }
        return commandGiven;
    }
}
