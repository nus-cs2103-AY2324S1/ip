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
        commandMessage = "I do not understand";
    }
}
