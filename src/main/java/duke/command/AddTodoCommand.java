package duke.command;

import duke.error.DukeException;
import duke.task.Todo;

/**
 * Represents a command to add a task.
 */
public class AddTodoCommand extends AddCommand {
    private final static String USAGE_TEXT = "todo <desc> | t <desc>";

    /**
     * Constructs a AddTodoCommand with the specified parameters.
     *
     * @param params The parameters associated with the command.
     * @throws DukeException If there's an issue validating or retrieving the parameter.
     */
    public AddTodoCommand(Params params) throws DukeException {
        super(new Todo(params.getArgumentIfSet("desc", USAGE_TEXT)), USAGE_TEXT);
    }
}
