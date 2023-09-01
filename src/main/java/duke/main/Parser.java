package duke.main;

import duke.command.*;
import duke.exception.*;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Parser class is responsible for parsing user input, understanding the command and return
 * the appropriate Command objects.
 */
public class Parser {
    /**
     * Parses the given full command by user and returns corresponding Command object.
     *
     * @param fullCommand The full user input command.
     * @return A Command object representing the parsed command.
     * @throws DukeException If there's an error during parsing.
     */
    public static Command parse(String fullCommand) throws DukeException {
        Command c;
        String[] splitCommand = fullCommand.split(" ", 2);
        String headCommand = splitCommand[0];
        String commandDetails;

        if (headCommand.equals("bye")) return new ExitCommand(null);

        if (headCommand.equals("list")) return new ListCommand(null);

        if (splitCommand.length < 2) throw new EmptyTaskException();

        commandDetails = splitCommand[1];
        String task;
        String[] details;
        int index;

        switch(headCommand) {
            case "delete":
                index = Integer.parseInt(commandDetails) - 1;
                return new DeleteCommand(index);

            case "mark":
                index = Integer.parseInt(commandDetails) - 1;
                return new MarkCommand(index, true);

            case "unmark":
                index = Integer.parseInt(commandDetails) - 1;
                return new MarkCommand(index, false);

            case "find":
                return new FindCommand(commandDetails);

            case "todo":
                task = commandDetails;
                Todo todo = new Todo(task, false);
                return new AddCommand(todo);

            case "deadline":
                details = commandDetails.split(" /by ");
                if (details.length < 2) throw new NoDueDateException();
                task = details[0];
                String duedate = details[1];
                Deadline deadline = new Deadline(task, duedate, false);
                return new AddCommand(deadline);

            case "event":
                details = commandDetails.split(" /from ");
                if(details.length < 2) throw new NoEventStartException();
                task = details[0];
                String[] timeDetails = details[1].split(" /to ");
                if(timeDetails.length < 2) throw new NoEventEndException();
                String start = timeDetails[0];
                String end = timeDetails[1];
                Event event = new Event(task, start, end, false);
                return new AddCommand(event);

            default:
                throw new DukeException("    TWEET!!! No such command");

        }
    }
}
