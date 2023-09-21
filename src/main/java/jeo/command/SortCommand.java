package jeo.command;

import jeo.storage.Storage;
import jeo.task.TaskList;
import jeo.ui.Ui;

/**
 * Represents the Command Sort that sort the deadlines chronologically.
 *
 * @author Joseph Oliver Lim
 */
public class SortCommand extends Command {
    /**
     * Constructs a SortCommand.
     */
    public SortCommand() {
        super(true);
    }

    /**
     * Executes the SortCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.sort();
        return ui.sortTasks(tasks);
    }
}
