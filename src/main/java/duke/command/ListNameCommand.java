package duke.command;

import duke.exception.KoraException;
import duke.list.CommandList;
import duke.list.TaskList;
import duke.storage.Storage;

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
     * Exits the program.
     * @param taskList List with tasks.
     * @param storage Storage where tasks are stored.
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {

    }

    @Override
    public void executeSet(CommandList commandList, Storage storage) throws KoraException {
        String allCommandName = commandList.listAllCommandName();
        commandMessage = "Here are names for each command: \n" + allCommandName;
    }

    @Override
    public boolean isSetCommand() {
        return true;
    }
}
