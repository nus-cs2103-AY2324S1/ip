package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.DukeList;
import duke.tasks.Task;
import duke.ui.Ui;

/**
 * Represents a command to find a task
 */
public class FindCommand extends Command {

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the command to find a task
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DukeException
     */
    public String execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> filteredTasks = tasks.filterByKeyword(this.keyword);
        storage.updateStorage(tasks.getArrayList());
        return ui.acknowledgeFind(filteredTasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
