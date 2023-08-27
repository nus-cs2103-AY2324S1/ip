package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.Task;
import robert.task.TaskList;
import robert.ui.Ui;

/**
 * A Find extension of the <tt>Command</tt> class. Used to find tasks
 * that are associated to a particular keyword.
 *
 * @author Lee Zhan Peng
 */
public class FindCommand extends Command {

    /** The keyword that is used for querying */
    private final String keyword;

    /**
     * Constructs FindCommand using a date.
     *
     * @param keyword the keyword to be used for querying of tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the finding of associated tasks.
     *
     * @param tasks the list of tasks to be added onto.
     * @param ui the ui that is responsible for the output of the CLI.
     * @param storage the storage that loads stored tasks from hard disk.
     * @throws RobertException as a mean of overriding the function.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        int taskIndex = 0;
        StringBuilder taskListing = new StringBuilder("Below is/are the task(s) associated with '"
                + this.keyword + "':\n");
        for (Task task : tasks) {
            if (task.getDescription().contains(this.keyword)) {
                taskIndex++;
                taskListing.append(String.format("%d. %s\n", taskIndex, task));
            }
        }

        if (taskIndex != 0) {
            ui.showMessage(taskListing.toString());
            return;
        }

        ui.showMessage("Sorry, there are no tasks that are associated with '"
                + this.keyword + "'.");
    }

}
