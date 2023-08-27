package robert.command;

import robert.ui.Ui;
import robert.storage.Storage;
import robert.task.TaskList;

import robert.exception.RobertException;

/**
 * An Exit extension of the <tt>Command</tt> class. Used to indicate the exit of
 * Robert from CLI.
 *
 * @author Lee Zhan Peng
 */
public class ExitCommand extends Command {

    /**
     * Executes the display of exit message.
     *
     * @param tasks the list of tasks to be added onto.
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        ui.showMessage("Goodbye. Hope to see you again soon!");
    }

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
