package duke.command;

import duke.exception.KoraException;
import duke.list.CommandList;
import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Add a specified command word for specified command type.
 */
public class SetCommand extends Command {
    private String commandMessage = "";

    private String commandType;
    private String commandName;

    /**
     * Class constructor of SetCommand.
     * @param input New Command name and its Command type.
     */
    public SetCommand(String input) {
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
     * Adds new Command name to specified Command type.
     * @param commandList List with command names.
     * @param storage Storage where command names are stored.
     * @throws KoraException When there is error saving edited command list in Storage.
     */
    @Override
    public void executeSet(CommandList commandList, Storage storage) throws KoraException {
        commandList.addCommandName(commandType, commandName);
        storage.saveCommand(commandList);
        commandMessage = "Wow! I have successfully added the command name: " + commandName + " for " + commandType;
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
