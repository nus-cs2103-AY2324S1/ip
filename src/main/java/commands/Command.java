package commands;

import errors.DotException;

/**
 * Responsible for execution of the app's logic.
 */
public abstract class Command {

    /**
     * Default implementation is to do nothing,
     * which is only utilised by ByeCommand.
     *
     * @throws DotException passing the buck
     */
    public void execute() throws DotException { };

    /**
     * Default implementation is to return false,
     * as all commands except for ByeCommand are
     * non-terminating.
     *
     * @return false unless overridden
     */
    public boolean isTerminateCommand() {
        return false;
    };
}
