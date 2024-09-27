package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents the command of listing all tasks.
 */
public class ListCommand extends Command {
    private final boolean isSorted;

    public ListCommand(boolean isSorted) {
        this.isSorted = isSorted;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert ui != null;
        return ui.showList(this.isSorted ? tasks.sorted() : tasks);
    }
}
