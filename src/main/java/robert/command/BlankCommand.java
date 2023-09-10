package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;

/**
 * A Blank extension of the <tt>Command</tt> class. Used to indicate a lack of response by
 * Robert due to empty user input.
 *
 * @author Lee Zhan Peng
 */
public class BlankCommand extends Command {

    /**
     * Executes nothing.
     *
     * @param tasks the list of tasks to be added onto.
     * @param storage the storage that loads stored tasks from hard disk.
     * @return Empty string.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RobertException {
        return "";
    }
}
