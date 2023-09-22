package noelPackage.command;

import noelPackage.helper.Storage;
import noelPackage.helper.Tasklist;

/**
 * Represents an abstract command that can be executed.
 * Subclasses must implement the execute method to provide specific behavior.
 */
public abstract class Command {

    /**
     * Executes the command.
     *
     * @param tasks   The list of tasks the user has.
     * @param storage The storage helper responsible for saving and loading tasks.
     * @return A string representing the result of executing the command.
     */
    public abstract String execute(Tasklist tasks, Storage storage);

}