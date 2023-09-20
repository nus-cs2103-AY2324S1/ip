package duke.command.alias;

import duke.alias.AliasMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `ListAliasCommand` class represents a command to list the tasks in the task list.
 */
public class ListAliasCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException {
        return ui.listAlias(aliases);
    }
}
