package duke.command;

import duke.main.Storage;
import duke.task.TaskList;
import javafx.application.Platform;

/**
 * A class for the command for terminating the Duke bot.
 */
public class ByeCommand extends Command {
    @Override
    public String execute(TaskList taskList, Storage storage, CommandList commandList, boolean write) {
        Platform.exit();
        return this.printCommand(taskList);
    }

    @Override
    public String printCommand(TaskList taskList) {
        return "";
    }
}
