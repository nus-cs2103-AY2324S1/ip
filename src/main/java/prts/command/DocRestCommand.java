package prts.command;

import prts.Storage;
import prts.TaskList;
import prts.Ui;

/**
 * A personality command that simply displays a message to the user depending on the state of the
 * TaskList.
 */
public class DocRestCommand extends Command {

    /**
     * Displays a message to the user that depends on whether the TaskList is currently empty.
     * @param tasks The list of tasks currently stored.
     * @param ui The UI object stored by PRTS.
     * @param storage The Storage object stored by PRTS.
     * @return The string to be displayed to the user upon successful execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return tasks.docRest();
    }

}
