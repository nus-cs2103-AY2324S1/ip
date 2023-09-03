package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;

/**
 * An Exit extension of the <tt>Command</tt> class. Used to indicate the exit of
 * Robert from CLI.
 *
 * @author Lee Zhan Peng
 */
public class ExitCommand extends Command {

    /**
     * Retrieves a boolean on whether the Command is an Exit Command.
     *
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * Executes the display of exit message.
     *
     * @param tasks the list of tasks to be added onto.
     * @param storage the storage that loads stored tasks from hard disk.
     * @return String of output message.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RobertException {
        return "Goodbye. Hope to see you again soon! Closing in a few seconds...";
    }
}
