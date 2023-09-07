package prts.command;

import prts.Storage;
import prts.TaskList;
import prts.Ui;

/**
 * The command for exiting PRTS.
 */
public class ExitCommand extends Command {

    /**
     * Does nothing. A concrete implementation is simply required as Command is abstract.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {}

    /**
     * Returns true to signal that the program should exit.
     * @return True, always.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
