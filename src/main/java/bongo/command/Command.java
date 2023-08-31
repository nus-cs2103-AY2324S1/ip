package bongo.command;

import bongo.task.TaskList;
import bongo.helper.BongoException;
import bongo.helper.Ui;
import bongo.helper.Storage;

public abstract class Command {

    boolean isExit;

    /**
     * Execute the respective details of the Command.
     *
     * @param tasks   TaskList instance.
     * @param ui      Ui instance.
     * @param storage Storage instance.
     * @throws BongoException If command is invalid.
     */
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException;

    /**
     * A constructor for a Command.
     */
    public Command() {
        this.isExit = false;
    }

    /**
     * Returns whether Command is an ExitCommand.
     *
     * @return Whether Command is an ExitCommand.
     */
    public boolean isExit() {
        return false;
    }
}
