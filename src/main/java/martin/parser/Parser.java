package martin.parser;

import martin.exceptions.InvalidCommandException;
import martin.exceptions.MartinException;
import martin.task.Task;
import martin.task.TaskList;

import java.util.ArrayList;

import martin.commands.ByeCommand;
import martin.commands.ListCommand;
import martin.commands.MarkCommand;
import martin.commands.TodoCommand;
import martin.commands.UnmarkCommand;
import martin.commands.Command;
import martin.commands.DateCommand;
import martin.commands.DeadlineCommand;
import martin.commands.DeleteCommand;
import martin.commands.EventCommand;


public class Parser {

    private TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the user input and returns the appropriate Command object.
     * @param input The entire string of the user's input.
     * @throws MartinException When the command is invalid or there's an error.
     * @return Command The appropriate command to be executed.
     */
    public Command parse(String input) throws MartinException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        ArrayList<Task> tasks = taskList.getTasks();

        switch (command) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand(tasks);
            case "delete":
                return new DeleteCommand(input, tasks);
            case "mark":
                return new MarkCommand(input, tasks);
            case "unmark":
                return new UnmarkCommand(input, tasks);
            case "todo":
                return new TodoCommand(input, tasks);
            case "deadline":
                return new DeadlineCommand(input, tasks);
            case "event":
                return new EventCommand(input, tasks);
            case "date":
                return new DateCommand(input, tasks);
            default:
                throw new InvalidCommandException("Unknown command: " + input);
        }
    }
}
