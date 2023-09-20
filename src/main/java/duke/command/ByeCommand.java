package duke.command;

import duke.alias.AliasMap;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `ByeCommand` class represents a command to exit the Duke application.
 */
public class ByeCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException {
        this.willExitNext = true;
        return ui.bye();
    }
}
