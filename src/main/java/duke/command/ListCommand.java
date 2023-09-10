package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command List that display all the task on the list
 *
 * @author Angky Akdi Frandy Putrakelana
 */
public class ListCommand extends Command {

    /**
     * Executes the ListCommand and returns the message.
     *
     * @param tasks The list of tasks.
     * @param ui The Ui that used as user interface.
     * @param storage The Storage that used to store, read and write data.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showList(tasks);
    }

    /**
     * Check if it is an ExitCommand
     *
     * @return a boolean that represent whether this is an ExitCommand or not.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
