package duke.command;

import duke.error.DukeException;
import duke.parser.Validate;
import duke.task.Event;

/**
 * Represents a command to add a task.
 */
public class AddEventCommand extends AddCommand {
    private final static String USAGE_TEXT = "event <desc> /from <date time> /to <date time>"
        + "\n\t\te <desc> /from <date time> /to <date time>";

    /**
     * Constructs a AddEventCommand with the specified parameters.
     *
     * @param params The parameters associated with the command.
     * @throws DukeException If there's an issue validating or retrieving the parameter.
     */
    public AddEventCommand(Params params) throws DukeException {
        super(new Event(params.getArgumentIfSet("desc", USAGE_TEXT),
                Validate.validateLocalDateTime(params.getParamIfSet("from", USAGE_TEXT)),
                Validate.validateLocalDateTime(params.getParamIfSet("to", USAGE_TEXT))),
            USAGE_TEXT);
    }
}
