package io;

import commands.AddTaskCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.LoadCommand;
import commands.MarkCommand;
import commands.SaveCommand;
import commands.ScheduleCommand;
import commands.UnmarkCommand;

/**
 * Represents a parser that parses the user input.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     * @param fullCommand
     * @return Command
     */
    public static Command parse(String fullCommand) {
        String[] commandArray = fullCommand.split(" ");
        assert commandArray.length > 0 : "commandArray should not be empty";
        String commandType = commandArray[0];
        Command command;
        switch (commandType) {
        case "bye":
            command = new ExitCommand();
            break;
        case "list":
            command = new ListCommand();
            break;
        case "save":
            command = new SaveCommand();
            break;
        case "load":
            command = new LoadCommand();
            break;
        case "mark":
            command = new MarkCommand(fullCommand);
            break;
        case "unmark":
            command = new UnmarkCommand(fullCommand);
            break;
        case "todo":
            command = new AddTaskCommand(fullCommand);
            break;
        case "deadline":
            command = new AddTaskCommand(fullCommand);
            break;
        case "event":
            command = new AddTaskCommand(fullCommand);
            break;
        case "delete":
            command = new DeleteCommand(fullCommand);
            break;
        case "schedule":
            command = new ScheduleCommand(fullCommand);
            break;
        case "find":
            command = new FindCommand(fullCommand);
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }

}
