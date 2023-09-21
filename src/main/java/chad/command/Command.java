package chad.command;

import chad.util.Storage;
import chad.util.TaskList;
import chad.util.Ui;

/**
 * Represents commands to be executed.
 */
public abstract class Command {
    private boolean isExit;

    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Executes the list of commands associated with the Command object.
     *
     * @param taskList The given TaskList required for execution of some commands.
     * @param ui The given Ui required for execution of some commands.
     * @param storage The given Storage required for execution of some commands.
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    /**
     * Returns a boolean value to indicate if Command object is an exit command.
     *
     * @return True if the Command object is an ExitCommand.
     */
    public boolean isExit() {
        return this.isExit;
    }
}
