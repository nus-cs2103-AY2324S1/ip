package io;
import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.InvalidCommand;
import commands.ListCommand;
import commands.LoadCommand;
import commands.MarkCommand;
import commands.SaveCommand;
import commands.ScheduleCommand;
import commands.UnmarkCommand;

public class Parser {
    private Command command;

    public Parser(Command command) {
        this.command = command;
    }

    public static Command parse(String fullCommand) {
        String[] commandArray = fullCommand.split(" ");
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
            command = new AddCommand(fullCommand);
            break;
        case "deadline":
            command = new AddCommand(fullCommand);
            break;
        case "event":
            command = new AddCommand(fullCommand);
            break;
        case "delete":
            command = new DeleteCommand(fullCommand);
            break;
        case "schedule":
            command = new ScheduleCommand(fullCommand);
            break;
        default:
            command = new InvalidCommand();
            break;
        }
        return command;
    }

}
