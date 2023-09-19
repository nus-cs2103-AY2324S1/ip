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

    public UnsetCommand(String input) {
        commandType = input.split(" ")[1];
        commandName = input.split(" ")[2];
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) throws KoraException {
    }

    @Override
    public void executeSet(CommandList commandList, Storage storage) throws KoraException {
        commandList.deleteCommandName(commandType, commandName);
        storage.saveCommand(commandList);
        commandMessage = "Wow! I have successfully deleted the command name: " + commandName + " for " + commandType;
    }
    @Override
    public boolean isSetCommand() {
        return true;
    }
}
