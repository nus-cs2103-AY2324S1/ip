package commands;

import errors.DotException;

/**
 * Responsible for execution of the app's logic
 */
public abstract class Command {

    public void execute() throws DotException { };
    public boolean isTerminateComamnd() {
        return false;
    };
}
