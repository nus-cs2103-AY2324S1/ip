package duke.command.alias;

import duke.alias.AliasMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `DeleteAliasCommand` class represents a command to delete an alias existing.
 */
public class DeleteAliasCommand extends Command {
    private String alias;
    /**
     * Constructs a new `DeleteAliasCommand` with the specified alias.
     * @param alias The alias to be deleted.
     */
    public DeleteAliasCommand(String alias) {
        this.alias = alias;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException {
        String fullCommand = aliases.getFullCommand(alias);
        if (fullCommand == null) {
            throw new DukeException("Alias `" + alias + "` not found!");
        }
        aliases.removeAlias(alias);
        return ui.deleteAlias(alias, fullCommand);
    }


}
