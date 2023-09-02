package haste.commands;

import haste.data.Storage;
import haste.data.TaskList;
import haste.ui.Ui;

/**
 * Represents a command that exits Haste.
 */
public class ExitCommand extends Command {
    private Storage store;

    /**
     * Creates an ExitCommand
     *
     * @param store Storage which contains data of tasks.
     */
    public ExitCommand(Storage store) {
        this.store = store;
    }

    /**
     * Executes the command.
     *
     * @param tasks TaskList containing the tasks.
     * @param ui Ui that handles interactions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.bye();
        this.store.delete();
        this.store.save(tasks);
    }
}
