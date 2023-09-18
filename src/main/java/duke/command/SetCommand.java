package duke.command;

import duke.exception.KoraException;
import duke.list.CommandList;
import duke.list.TaskList;
import duke.storage.Storage;

public class SetCommand extends Command {
    private String commandMessage = "";

    private String commandType;
    private String commandName;

    public SetCommand(String input) {
        commandType = input.split(" ")[1];
        commandName = input.split(" ")[2];
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {

    }

    @Override
    public void executeSet(CommandList commandList, Storage storage) throws KoraException {
        commandList.addCommandName(commandType, commandName);
        storage.saveCommand(commandList);
        commandMessage = "Wow! I have successfully added the command name: " + commandName + " for " + commandType;
    }
    @Override
    public boolean isSetCommand() {
        return true;
    }
}
