package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `FindCommand` class represents a command to find tasks based on their names.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a new `FindCommand` with the specified keyword.
     *
     * @param keyword The keyword to be used to search.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> result = items.searchFor(keyword);
        ui.listSearch(result);
    }
}
