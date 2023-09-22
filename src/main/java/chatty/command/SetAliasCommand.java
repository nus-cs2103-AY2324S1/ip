package chatty.command;

import chatty.exception.ChattyException;
import chatty.task.TaskList;
import chatty.utils.Parser;
import chatty.utils.Storage;
import chatty.utils.Ui;

import java.io.IOException;


/**
 * Represents a command to set a custom alias for a command.
 * This command allows users to define their own command aliases.
 */
public class SetAliasCommand extends Command {

    private String alias;
    private String command;

    /**
     * Constructs a SetAliasCommand instance.
     *
     * @param alias The custom alias to set.
     * @param command The command associated with the alias.
     */
    public SetAliasCommand(String alias, String command) {
        super(false);
        this.alias = alias;
        this.command = command;
    }


    /**
     * Executes the SetAliasCommand by setting a custom alias for a command.
     *
     * @param taskList The task list with the current available tasks.
     * @param ui The current user interface for displaying messages.
     * @param storage The storage class that is responsible for updating stored data.
     * @return A message indicating that the alias has been set.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws ChattyException {
        int value = Parser.setAlias(alias, command);
        if (value == -1) {
            return ui.showInUse(alias);
        }
        try {
            if (value == 1) {
                storage.updateAlias(alias);
                return ui.showReplace(alias, command);
            } else {
                storage.saveAlias(alias, command);
                return ui.showSet(alias, command);
            }
        } catch (Exception e) {
            throw new ChattyException("unable to update / save alias");
        }
    }
}
