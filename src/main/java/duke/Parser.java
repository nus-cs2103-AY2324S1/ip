package duke;
import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.MarkCommand;
import duke.command.UnMarkCommand;
import duke.command.ListCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.task.Event;
import duke.task.Deadline;
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
            case ("event"): {
                String[] tem_1 = splits[1].split("/from", 2);  // Using splits[1] since we've already split at "event"
                String[] tem_2 = tem_1[1].split("/to", 2);
                String description = tem_1[0].trim();
                String start = tem_2[0].trim();
                String end = tem_2[1].trim();
                return new AddCommand(new Event(description, start, end));
            }
            case ("deadline"): {
                String[] tem = splits[1].split("/by", 2);  // Using splits[1] since we've already split at "deadline"
                String description = tem[0].trim();
                String time = tem[1].trim();
                return new AddCommand(new Deadline(description, time));
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
