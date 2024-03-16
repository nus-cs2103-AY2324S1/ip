package duke.command;

import duke.error.DukeException;
import duke.parser.Validate;
import duke.task.Deadline;

/**
 * Represents a command to add a task.
 */
public class AddDeadlineCommand extends AddCommand {
    private static final String USAGE_TEXT = "deadline <desc> /by <date time>"
        + "\n\t\td <desc> /by <date time>";

    /**
     * Constructs a AddDeadlineCommand with the specified parameters.
     *
     * @param params The parameters associated with the command.
     * @throws DukeException If there's an issue validating or retrieving the parameter.
     */
    public AddDeadlineCommand(Params params) throws DukeException {
        super(new Deadline(params.getArgumentIfSet("desc", USAGE_TEXT),
                Validate.validateLocalDateTime(params.getParamIfSet("by", USAGE_TEXT))),
            USAGE_TEXT);
    }
}
