package duke.command;

import duke.DukeException;

public abstract class NonemptyArgumentCommand {

    protected void validate(String arguments) throws DukeException {
        if (arguments == null) {
            throw new DukeException(String.format("Missing Argument for NonemptyArgumentCommand: %s", this));
        }
    }
}
