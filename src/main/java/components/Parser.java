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
import tasks.ToDo;

public class Parser {
    /**
     * Create a ToDo task.
     *
     * @param command Command to be parsed.
     * @return ToDo task.
     * @throws DukeException If command is invalid.
     */
    public static ToDo createToDoTask(String command) throws DukeException {
        try {
            command.substring(5);
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("todo command must be followed by a space and a string. ERR: STRING INDEX OUT OF BOUNDS.");
            } 
        
            String description = command.substring(5);

            if (description.isEmpty()) {
                throw new DukeException("todo command must be followed by a space and a string. ERR: NO STRING.");
            } 
        return new ToDo(description);
    }

    /**
     * Create a Deadline task.
     *
     * @param fullCommand Command to be parsed.
     * @return Deadline task.
     * @throws DukeException If command is invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] command = fullCommand.split(" ");

        switch (command[0]) {
        case "bye":
            return new ExitCommand();

        case "list":
            return new ShowCommand();

        case "mark":
            try {
                Integer.valueOf(fullCommand.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Mark command must be followed by a space and an integer. ERR: STRING INDEX OUT OF BOUNDS.");
            } catch (NumberFormatException e) {
                throw new DukeException("Mark command must be followed by a space and an integer. ERR: NOT AN INTEGER.");
            } 

            return new MarkAsDoneCommand(Integer.valueOf(fullCommand.substring(5)));

        case "unmark":
            return new MarkAsUndoneCommand(Integer.valueOf(fullCommand.substring(7)));

        case "todo":
            return new ToDoCommand(fullCommand);

        case "deadline":
            return new DeadlineCommand(fullCommand);

        case "event":
            return new EventCommand(fullCommand);

        case "delete":
            return new DeleteCommand(Integer.parseInt(fullCommand.substring(7)));

        case "find":
            return new FindCommand(fullCommand.substring(5));

        default:
            return new UnknownCommand();
        }
    }
}
