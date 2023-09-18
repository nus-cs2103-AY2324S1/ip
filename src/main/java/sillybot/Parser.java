package sillybot;

import sillybot.commands.AddTaskCommand;
import sillybot.commands.ByeCommand;
import sillybot.commands.Command;
import sillybot.commands.DeleteCommand;
import sillybot.commands.ExitCommand;
import sillybot.commands.FindCommand;
import sillybot.commands.HelpCommand;
import sillybot.commands.ListCommand;
import sillybot.commands.MarkCommand;
import sillybot.commands.UnmarkCommand;

/**
 * Represents a Parser object that deals with making sense of the user command.
 */
public class Parser {
    /**
     * Creates a Parser object.
     */
    public Parser() {
    }

    /**
     * Checks the type of command given by the user.
     * Executes the command given by the user.
     *
     * @param commandGiven The command given by the user.
     * @return The String containing the output of the command.
     */
    public static Command parse(String commandGiven) {
        int taskNumber;
        commandGiven = commandGiven.trim();
        String[] splittedCommands = commandGiven.split(" ", 2);
        String commandType = splittedCommands[0];
        Command command;

        switch (commandType) {
        case "bye":
            command = new ByeCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "find":
            String keyword = splittedCommands[1];
            command = new FindCommand(keyword);
            break;
        case "mark":
            taskNumber = Integer.parseInt(splittedCommands[1]);
            command = new MarkCommand(taskNumber);
            break;
        case "unmark":
            taskNumber = Integer.parseInt(splittedCommands[1]);
            command = new UnmarkCommand(taskNumber);
            break;
        case "delete":
            taskNumber = Integer.parseInt(splittedCommands[1]);
            command = new DeleteCommand(taskNumber - 1);
            break;
        case "help":
            command = new HelpCommand();
            break;
        case "exit":
            command = new ExitCommand();
            break;
        default:
            command = new AddTaskCommand(commandGiven);
            break;
        }

        return command;
    }
}
