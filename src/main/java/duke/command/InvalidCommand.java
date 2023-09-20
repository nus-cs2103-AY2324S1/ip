package duke.command;

import duke.list.TaskList;
import duke.storage.Storage;

/**
 * Subclass of Command class. Creates invalid command with message.
 */
public class InvalidCommand extends Command {
    private String commandMessage = "";
    public InvalidCommand() {
    }

    @Override
    public String getCommandMessage() {
        return commandMessage;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        commandMessage = "Mi an,, I do not understand. Maybe can type 'help' to find out the commands?";
    }
}
