package robert.command;

import robert.ui.Ui;
import robert.storage.Storage;
import robert.task.TaskList;

import robert.task.Task;

import robert.exception.RobertException;

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
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        if (tasks.getTaskCount() == 0) {
            ui.showMessage("You do not have any tasks stored. Add one!");
            return;
        }

        int taskIndex = 1;
        StringBuilder taskListing = new StringBuilder("Here are the tasks in your list:\n");
        for (Task task : tasks) {
            taskListing.append(String.format("%d. %s\n", taskIndex, task));
            taskIndex++;
        }
        ui.showMessage(taskListing.toString());
    }
}
