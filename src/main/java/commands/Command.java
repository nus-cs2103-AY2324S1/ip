package commands;

import errors.DotException;

/**
 * Responsible for execution of the app's logic.
 */
public abstract class Command {

    /**
     * Executes the operations of the command.
     *
     * @throws DotException passing the buck.
     */
    public void execute() throws DotException {
    }

    /**
     * Returns true if Command should terminate the program.
     *
     * @return false unless overridden.
     */
    public boolean isTerminateCommand() {
        return false;
    }

}
