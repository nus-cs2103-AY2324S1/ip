package duke.command.task;

import java.util.ArrayList;

import duke.alias.AliasMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `FindCommand` class represents a command to find tasks based on their names.
 */
public class FindTaskCommand extends Command {

    private String keyword;

    /**
     * Constructs a new `FindCommand` with the specified keyword.
     *
     * @param keyword The keyword to be used to search.
     */
    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> result = items.searchFor(keyword);
        return ui.listSearch(result);
    }
}
