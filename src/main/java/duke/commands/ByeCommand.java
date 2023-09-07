package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.data.Message;

/**
 * The ByeCommand display a goodbye message when it is executed.
 */
public class ByeCommand extends Command {

    public ByeCommand() { }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) {
        return message.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
