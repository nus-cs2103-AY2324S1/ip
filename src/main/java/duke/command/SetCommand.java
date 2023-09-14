package duke.command;

import duke.storage.Storage;

public class SetCommand extends Command {
    private String commandMessage = "";

    private String commandType;
    private String commandName;

    public SetCommand(String type, String name) {
        commandType = type;
        commandName = name;
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    public void execute(CommandList, commandList, Storage storage) {
        if (taskList.getLength() == 0) {
            commandMessage = "Wow! You have no tasks!";
        } else {
            commandMessage = "Here are your tasks: " + "\n" + taskList.toString();
        }
    }
}
