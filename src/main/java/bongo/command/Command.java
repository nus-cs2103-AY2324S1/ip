package bongo.command;

import bongo.helper.BongoException;
import bongo.helper.Storage;
import bongo.helper.Ui;
import bongo.task.TaskList;

/**
 * A class for a Command.
 */
public abstract class Command {

    /**
     * Execute the respective details of the Command.
     *
     * @param tasks   TaskList instance.
     * @param ui      Ui instance.
     * @param storage Storage instance.
     * @throws BongoException If command is invalid.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException;


    /**
     * Returns whether Command is an ExitCommand.
     *
     * @return Whether Command is an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
