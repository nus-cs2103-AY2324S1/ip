package duke.command.alias;

import duke.alias.AliasMap;
import duke.command.Command;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The `AddAliasCommand` class represents a command to add a new alias for given command.
 */
public class AddAliasCommand extends Command {

    private String alias;
    private String fullCommand;

    /**
     * Constructs a new `AddAliasCommand` with the specified alias and fullCommand.
     * @param alias The alias string, which will represent the fullCommand.
     * @param fullCommand The full command for the alias.
     */
    public AddAliasCommand(String alias, String fullCommand) {
        this.alias = alias;
        this.fullCommand = fullCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList items, AliasMap aliases, Ui ui, Storage storage) throws DukeException {
        aliases.addAlias(alias, fullCommand);
        storage.writeAlias(aliases);
        return ui.addAlias(alias, fullCommand);
    }
}
