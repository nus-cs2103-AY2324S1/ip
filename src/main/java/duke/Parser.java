package duke;

import duke.commands.AddTaskCommand;
import duke.commands.ByeCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DetailedHelpCommand;
import duke.commands.FindCommand;
import duke.commands.HelpCommand;
import duke.commands.ListCommand;
import duke.commands.MarkCommand;
import duke.commands.UnmarkCommand;
import duke.exception.UnknownCommandException;
import duke.exception.WrongMarkException;

/**
 * Describes the actions taken by the bot when commands is given
 */
public class Parser {
    /**
     * Describes the action taken
     * 
     * @param command  User command
     * @return a command that is to be executed
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
        case "help":
            if (splittedCommand.length > 1) {
                int helpNumber = checkExceptionForIndex(splittedCommand);
                commandGiven = new DetailedHelpCommand(helpNumber);
            } else {
                commandGiven = new HelpCommand();
            }
            break;
        case "bye":
            commandGiven = new ByeCommand(command);
            break;
        case "find":
            String keyword = command.replace("find ", "");
            commandGiven = new FindCommand(keyword);
            break;
        case "list":
            commandGiven = new ListCommand(command);
            break;
        case "mark":
            taskNumber = checkExceptionForIndex(splittedCommand);
            commandGiven = new MarkCommand(taskNumber);
            break;
        case "unmark":
            taskNumber = checkExceptionForIndex(splittedCommand);
            commandGiven = new UnmarkCommand(taskNumber);
            break;
        case "delete":
            taskNumber = checkExceptionForIndex(splittedCommand);
            commandGiven = new DeleteCommand(taskNumber);
            break;
        default:
            commandGiven = new AddTaskCommand(command);
        }
        return commandGiven;
    }

    private static int checkExceptionForIndex(String[] splittedCommand) throws UnknownCommandException {
        int taskNumber = 0;
        if (splittedCommand.length > 2) {
            throw new UnknownCommandException("What do you want??!!");
        }
        try {
            taskNumber = Integer.parseInt(splittedCommand[1]);
        } catch (NumberFormatException e) {
            throw new UnknownCommandException("How is this a number?!!");
        }
        return taskNumber;
    }
}
