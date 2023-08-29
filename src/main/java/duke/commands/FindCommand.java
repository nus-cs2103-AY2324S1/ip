package duke.commands;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the FindCommand Class
 * Responsible for handling the find operation.
 *
 * @author Shishir
 */
public class FindCommand extends Command {

    /** String entered by user. */
    String find;

    /**
     * Constructs the FindCommand object.
     * @param find Keyword to find.
     */
    public FindCommand(String find) {
        this.find = find;
    }

    /**
     *
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showFind(tasks.filterByName(this.find));
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
