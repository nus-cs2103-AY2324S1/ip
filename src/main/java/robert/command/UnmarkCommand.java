package robert.command;

import robert.ui.Ui;
import robert.storage.Storage;
import robert.task.TaskList;

import robert.exception.RobertException;

/**
 * An Unmark extension of the <tt>Command</tt> class. Used to unmark
 * a particular task in the list of tasks.
 *
 * @author Lee Zhan Peng
 */
public class UnmarkCommand extends Command {

    /** The index of task to be unmarked */
    private final int unmarkIndex;

    /**
     * Constructs UnmarkCommand using an index.
     *
     * @param unmarkIndex the index of task to be unmarked.
     */
    public UnmarkCommand(int unmarkIndex) {
        this.unmarkIndex = unmarkIndex;
    }

    /**
     * Executes the unmarking of the particular task.
     *
     * @param tasks the list of tasks to be added onto.
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.unmarkTask(this.unmarkIndex);
        ui.showMessage("Ok, I've marked this task as not done yet:\n  "
                + tasks.getTask(this.unmarkIndex));
    }
}
