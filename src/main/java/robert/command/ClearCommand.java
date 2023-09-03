package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;

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
     * @param storage the storage that loads stored tasks from hard disk.
     * @return String of output message.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RobertException {
        tasks.clearTasks();
        return "Understood. I've removed every task in your list.\n"
                + "Now your list of tasks is empty!";
    }
}
