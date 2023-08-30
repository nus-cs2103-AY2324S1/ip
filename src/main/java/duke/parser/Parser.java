package duke.parser;

import duke.commands.AddDeadlineCommand;
import duke.commands.AddEventCommand;
import duke.commands.AddToDoCommand;
import duke.commands.Command;
import duke.commands.CommandType;
import duke.commands.DeleteTaskCommand;
import duke.commands.ExitCommand;
import duke.commands.FindTaskCommand;
import duke.commands.ListTasksCommand;
import duke.commands.MarkTaskCommand;
import duke.commands.UnmarkTaskCommand;
import duke.exceptions.DukeInvalidCommandException;
import duke.tasks.TaskList;

/**
 * The parser is used to parse user input into commands.
 */
public class Parser {

    /** The task list to be used by the parser. */
    private final TaskList taskList;

    /**
     * Creates a new Parser object.
     *
     * @param taskList The task list to be used by the parser.
     */
    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses the given input into a command.
     *
     * @param input The input to be parsed.
     * @return The command that was parsed.
     * @throws DukeInvalidCommandException If the given input is invalid.
     */
    public Command parse(String input) throws DukeInvalidCommandException {
        String[] parts = input.split(" ", 2);
        CommandType commandType = CommandType.fromString(parts[0]);
        String commandArgs = parts.length > 1 ? parts[1].trim() : "";

        switch (commandType) {
        case EXIT:
            return new ExitCommand(this.taskList, commandArgs);

        case LIST_TASKS:
            return new ListTasksCommand(this.taskList, commandArgs);

        case ADD_TODO:
            return new AddToDoCommand(this.taskList, commandArgs);

        case ADD_DEADLINE:
            return new AddDeadlineCommand(this.taskList, commandArgs);

        case ADD_EVENT:
            return new AddEventCommand(this.taskList, commandArgs);

        case MARK_TASK:
            return new MarkTaskCommand(this.taskList, commandArgs);

        case UNMARK_TASK:
            return new UnmarkTaskCommand(this.taskList, commandArgs);

        case DELETE_TASK:
            return new DeleteTaskCommand(this.taskList, commandArgs);

        case FIND_TASK:
            return new FindTaskCommand(this.taskList, commandArgs);

        default:
            throw new DukeInvalidCommandException("I'm gonna be honest, no idea what you're saying.");
        }

    }
}
