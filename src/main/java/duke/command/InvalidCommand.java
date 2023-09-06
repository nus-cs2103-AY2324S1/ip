package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

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
