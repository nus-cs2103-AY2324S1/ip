package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * A Clear extension of the <tt>Command</tt> class. Removes all tasks from
 * the list of tasks.
 *
 * @author Lee Zhan Peng
 */
public class ClearCommand extends Command {

    /**
     * Executes the removal of all tasks from the list of tasks.
     *
     * @param tasks the list of tasks to be added onto.
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.clearTasks();
        ui.showMessage("Understood. I've removed every task in your list.\n"
                + "Now your list of tasks is empty!");
    }
}
