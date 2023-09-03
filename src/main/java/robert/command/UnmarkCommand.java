package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;

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
     * @param storage the storage that loads stored tasks from hard disk.
     * @return String of output message.
     * @throws RobertException if index is out of bounds of the list of tasks.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RobertException {
        tasks.unmarkTask(this.unmarkIndex);
        return "Ok, I've marked this task as not done yet:\n  "
                + tasks.getTask(this.unmarkIndex);
    }
}
