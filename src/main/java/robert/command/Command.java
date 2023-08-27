package robert.command;

import robert.ui.Ui;
import robert.storage.Storage;
import robert.task.TaskList;

import robert.exception.RobertException;

/**
 * The base class for commands.
 *
 * @author Lee Zhan Peng
 */
public class Command {

    /**
     * Executes the intended task for its subclass.
     *
     * @param tasks the list of tasks to be added onto.
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException to allow subclasses to override the function with the throw property.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
    }

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return false.
     */
    public boolean isExit() {
        return false;
    }
}
