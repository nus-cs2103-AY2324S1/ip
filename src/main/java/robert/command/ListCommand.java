package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Task;
import robert.task.TaskList;

/**
 * A List extension of the <tt>Command</tt> class. Used to list out
 * all stored tasks.
 *
 * @author Lee Zhan Peng
 */
public class ListCommand extends Command {

    /**
     * Executes the listing of all stored tasks.
     *
     * @param tasks the list of tasks to be added onto.
     * @param storage the storage that loads stored tasks from hard disk.
     * @return String of output message.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws RobertException {
        if (tasks.getTaskCount() == 0) {
            return "You do not have any tasks stored. Add one!";
        }

        int taskIndex = 1;
        StringBuilder taskListing = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            taskListing.append(String.format("%d. %s\n", taskIndex, task));
            taskIndex++;
        }
        return taskListing.toString();
    }
}
