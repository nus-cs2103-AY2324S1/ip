package duke.command;

import duke.DukeException;

public abstract class NumberedChoiceCommand extends NonemptyArgumentCommand {
    protected void validate(String arguments) throws DukeException {
        super.validate(arguments);
        try {
            int i = Integer.parseInt(arguments) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid Argument Provided, expected numeric argument: " + arguments);
        }
    }

}
