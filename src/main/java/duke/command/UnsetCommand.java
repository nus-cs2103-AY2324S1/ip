package duke.command;

import duke.exception.KoraException;
import duke.list.CommandList;
import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Delete a specified command word for specified command type.
 */
public class UnsetCommand extends Command {
    private String commandMessage = "";

    private String commandType;
    private String commandName;

    /**
     * Class constructor of UnsetCommand.
     * @param input Command name that is to be deleted and its Command type.
     */
    public UnsetCommand(String input) {
        commandType = input.split(" ")[1];
        commandName = input.split(" ")[2];
    }

    /**
     * {@inheritDoc}
     * @return {@inheritDoc}
     */
    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    /**
     * Does not do anything.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
    }

    /**
     * {@inheritDoc}
     *
     * Deletes the Command name from command list.
     * @param commandList List with command names.
     * @param storage Storage where command names are stored.
     * @throws KoraException When there is error saving edited command list in Storage.
     */
    @Override
    public void executeSet(CommandList commandList, Storage storage) throws KoraException {
        commandList.deleteCommandName(commandType, commandName);
        storage.saveCommand(commandList);
        commandMessage = "Wow! I have successfully deleted the command name: " + commandName + " for " + commandType;
    }

    /**
     * Returns true as it is command name related command.
     * @return True.
     */
    @Override
    public boolean isSetCommand() {
        return true;
    }
}
