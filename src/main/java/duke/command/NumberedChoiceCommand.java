package duke.command;

import duke.DukeException;

/**
 * Represents a command with a non-empty numerical argument.
 *
 * Should be of the structure [command] [number], where number is a task id.
 */
public abstract class NumberedChoiceCommand extends NonemptyArgumentCommand {

    /**
     * Validate arguments to this command.
     *
     * @param arguments arguments to validate
     * @throws DukeException if arguments are invalid
     */
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
        try {
            int i = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Argument Provided, expected numeric argument: " + arguments);
        }
    }

}
