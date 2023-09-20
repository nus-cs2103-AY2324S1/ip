package duke.command;

import duke.exception.DukeException;
import duke.util.Alias;
import duke.util.Response;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a <code>alias</code> command. It can add an alias or remove an existing one.
 * It can also list all the current aliases. A <code>AliasCommand</code> object
 * corresponds to an executable command when the user wants to add or remove an alias.
 */
public class AliasCommand extends Command {

    private final String commandBody;
    private final boolean isAddAlias;
    private final boolean isListAlias;

    /**
     * Constructs a <code>alias</code> command.
     *
     * @param commandBody The command body.
     */
    public AliasCommand(String commandBody) {
        super(false);
        this.commandBody = commandBody;
        this.isAddAlias = commandBody.contains(" ");
        this.isListAlias = false;
    }

    /**
     * Constructs a <code>alias</code> command. To list all the current aliases.
     */
    public AliasCommand() {
        super(false);
        this.commandBody = "";
        this.isAddAlias = false;
        this.isListAlias = true;
    }

    /**
     * Executes the alias command to either add an alias or remove an existing one.
     *
     * @param taskList The task list.
     * @param ui       The user interface.
     * @param storage  The storage.
     * @return the response to the user.
     * @throws DukeException If an error occurs during execution.
     */
    @Override
    public Response execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert taskList != null : "taskList should not be null";
        assert ui != null : "ui should not be null";
        assert storage != null : "storage should not be null";

        if (isListAlias) {
            return ui.showAliasList();
        }

        String aliasFrom;
        String aliasTo;
        if (isAddAlias) {
            String[] split = commandBody.split(" ");
            aliasFrom = split[0];
            aliasTo = commandBody.substring(aliasFrom.length() + 1);
            if (Alias.isAliasPresent(aliasTo)) {
                throw new DukeException("OOPS!!! Looks like you are trying to make "
                        + "an alias of an alias!\nThis is not allowed! Please try again!");
            }
            Alias.addAlias(aliasFrom, aliasTo);
        } else {
            aliasFrom = commandBody;
            if (!Alias.isAliasPresent(aliasFrom)) {
                throw new DukeException("OOPS!!! The alias is not found!");
            }
            aliasTo = Alias.getAlias(aliasFrom);
            Alias.removeAlias(aliasFrom);
        }

        storage.saveAlias(Alias.saveAliasFormat());
        return ui.showAlias(isAddAlias, aliasFrom, aliasTo);
    }
}
