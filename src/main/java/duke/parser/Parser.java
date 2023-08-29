package duke.parser;

import duke.DukeException;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.EventCommand;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.TodoCommand;
import duke.command.UnmarkCommand;

public class Parser {
    public static Command parse(String fullCommand) throws DukeException {
        String[] parts = fullCommand.split(" ", 2);
        String keyword = parts[0];
        String details = parts.length == 2 ? parts[1] : "";

        switch (keyword) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(details);
        case "unmark":
            return new UnmarkCommand(details);
        case "todo":
            return new TodoCommand(details);
        case "deadline":
            return new DeadlineCommand(details);
        case "event":
            return new EventCommand(details);
        case "delete":
            return new DeleteCommand(details);
        default:
            throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
