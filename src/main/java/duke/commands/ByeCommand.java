package duke.commands;

import duke.data.Message;
import duke.data.TaskList;
import duke.storage.Storage;

/**
 * Represents a command for displaying a goodbye message when executed.
 */
public class ByeCommand extends Command {

    public ByeCommand() { }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) {
        return message.showBye();
    }
}
