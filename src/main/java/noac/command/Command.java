package noac.command;

import noac.NoacException;
import noac.Storage;
import noac.TaskList;
import noac.Ui;

/**
 * Abstract class for all commands to extend from which includes the abstract
 * execute function and also the isExit function.
 */
public abstract class Command {

    private boolean isExit = false;

    /**
     * Abstract execute functions with the relevant parameters.
     *
     * @param tasks List of all the task.
     * @param ui UI for printing result to user.
     * @param storage Storage class meant for saving to file.
     * @throws NoacException For any errors that needs to be displayed to user.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException;

    /**
     * Determine if command is bye or not.
     *
     * @return The boolean for where to exit or not.
     */
    public boolean isExit() {
        return isExit;
    }
}
