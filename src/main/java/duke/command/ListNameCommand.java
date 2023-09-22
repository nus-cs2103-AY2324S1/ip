package duke.command;

import duke.exception.KoraException;
import duke.list.CommandList;
import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. List all names for each Command type.
 */
public class ListNameCommand extends Command {
    private String commandMessage = "";

    /**
     * Class constructor of ListNameCommand.
     */
    public ListNameCommand() {
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
     * {@inheritDoc}
     *
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
     * Generates message with all the Command names for each Command types.
     * @param commandList List with command names.
     * @param storage Storage where command names are stored.
     */
    @Override
    public void executeSet(CommandList commandList, Storage storage) {
        String allCommandName = commandList.listAllCommandName();
        commandMessage = "Here are names for each command: \n" + allCommandName;
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
