package duke.commands;

import duke.data.Message;
import duke.data.TaskList;
import duke.storage.Storage;

/**
 * The ByeCommand display a goodbye message when it is executed.
 */
public class ByeCommand extends Command {

    public ByeCommand() { }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) {
        return message.showBye();
    }
}
