package duke;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;
/**
 * Represents a parser that makes sense of user commands.
 */
public class Parser {

    /**
     * Parses the user's command and returns the corresponding command object.
     *
     * @param fullCommand The full string command provided by the user.
     * @return The corresponding Command object.
     * @throws DukeException If the command is unrecognized or invalid.
     */
    public static Command parse(String fullCommand) throws DukeException {
        String[] splits = fullCommand.split(" ", 2);
        switch (splits[0].trim()) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand();
        case ("mark"):
            return new MarkCommand(Integer.parseInt(splits[1].trim()) - 1);
        case ("unmark"):
            return new UnMarkCommand(Integer.parseInt(splits[1].trim()) - 1);
        case ("todo"):
            return new AddCommand(new Todo(splits[1].trim()));
        case ("event"):
            String[] temfirst = splits[1].split("/from", 2);
            String[] temsecond = temfirst[1].split("/to", 2);
            String description = temfirst[0].trim();
            String start = temsecond[0].trim();
            String end = temsecond[1].trim();
            return new AddCommand(new Event(start, end, description));
        case ("deadline"): {
            String[] tem = splits[1].split("/by", 2);
            String deadlineDescription = tem[0].trim();
            String time = tem[1].trim();
            return new AddCommand(new Deadline(deadlineDescription, time));
        }
        case ("delete"):
            return new DeleteCommand(Integer.parseInt(splits[1].trim()));
        case ("find"):
            return new FindCommand(splits[1].trim());
        default:
            throw new DukeException("☹ I'm sorry, but I don't know what that means :-(");
        }
    }
}
