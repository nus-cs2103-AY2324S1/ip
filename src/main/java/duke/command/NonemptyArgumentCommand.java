package duke.command;

import duke.DukeException;

/**
 * Represents an argument with one or more non-empty arguments.
 *
 * In the structure [command] [args]
 */
public abstract class NonemptyArgumentCommand {

    /**
     * Validate arguments to this command.
     *
     * @param arguments arguments to validate
     * @throws DukeException if arguments are invalid
     */
    protected void validate(String arguments) throws DukeException {
        if (arguments == null) {
            throw new DukeException(String.format("Missing Argument for NonemptyArgumentCommand: %s", this));
        }
    }
}
