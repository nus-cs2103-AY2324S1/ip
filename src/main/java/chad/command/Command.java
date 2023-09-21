package chad.command;

import chad.util.Storage;
import chad.util.TaskList;
import chad.util.Ui;

/**
 * Represents commands to be executed.
 */
public abstract class Command {

    /**
     * Executes the list of commands associated with the Command object.
     *
     * @param taskList The given TaskList required for execution of some commands.
     * @param ui The given Ui required for execution of some commands.
     * @param storage The given Storage required for execution of some commands.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
