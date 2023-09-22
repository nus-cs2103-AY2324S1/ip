package components;

import commands.Command;
import commands.DeadlineCommand;
import commands.DeleteCommand;
import commands.EventCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.MarkAsDoneCommand;
import commands.MarkAsUndoneCommand;
import commands.ShowCommand;
import commands.ToDoCommand;
import commands.UnknownCommand;
import commands.YesCommand;

import tasks.TaskList;
import tasks.ToDo;

public class Parser {
    /**
     * Parses the given command to create a ToDo task.
     *
     * @param command The command string that represents the ToDo task to be created.
     * @return A ToDo task created based on the parsed command.
     * @throws DukeException If the command string is invalid or cannot be parsed into a ToDo task.
     */
    public static ToDo createToDoTask(String command, TaskList list) throws DukeException {
        assert command != null : "Command should not be null";
        String description;

        try {
            description = command.substring(5);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("todo command must be followed " +
                    "by a space and a string. ERR: STRING INDEX OUT " +
                    "OF BOUNDS.");
        }

        assert !description.isEmpty() : "Description should not be empty";

        return new ToDo(description, list);
    }

    /**
     * Create a Deadline task.
     *
     * @param fullCommand Command to be parsed.
     * @return Deadline task.
     * @throws DukeException If command is invalid.
     */
    public Command parse(String fullCommand) throws DukeException {
        assert fullCommand != null && !fullCommand.trim().isEmpty() : "Command "
                + "should not be null or empty";

        String[] command = fullCommand.split(" ");
        assert command.length > 0 : "Split command should have at least one element";

        Command parsedCommand = null;

        switch (command[0].strip()) {
        case "bye":
            parsedCommand = new ExitCommand();
            break;

        case "list":
            parsedCommand = new ShowCommand();
            break;

        case "mark":
            try {
                Integer.valueOf(fullCommand.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Mark command must be followed "
                        + "by a space and an integer. ERR: STRING INDEX OUT OF BOUNDS.");
            } catch (NumberFormatException e) {
                throw new DukeException("Mark command must be followed "
                        + "by a space and an integer. ERR: NOT AN INTEGER.");
            }

            parsedCommand = new MarkAsDoneCommand(Integer.valueOf(fullCommand.substring(5)));
            break;

        case "unmark":
            parsedCommand = new MarkAsUndoneCommand(Integer.valueOf(fullCommand.substring(7)));
            break;

        case "todo":
            parsedCommand = new ToDoCommand(fullCommand);
            break;

        case "deadline":
            parsedCommand = new DeadlineCommand(fullCommand);
            break;

        case "event":
            parsedCommand = new EventCommand(fullCommand);
            break;

        case "delete":
            parsedCommand = new DeleteCommand(Integer.parseInt(fullCommand.substring(7)));
            break;

        case "yes":
            parsedCommand = new YesCommand();
            break;

        case "find":
            parsedCommand = new FindCommand(fullCommand.substring(5));
            break;

        default:
            parsedCommand = new UnknownCommand();
        }

        assert parsedCommand != null : "Parsed command should not be null";

        return parsedCommand;
    }
}
