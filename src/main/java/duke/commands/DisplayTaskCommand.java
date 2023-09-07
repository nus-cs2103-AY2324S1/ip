package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.data.Message;

/**
 * The DisplayTaskCommand display the task requested when it is executed.
 */
public class DisplayTaskCommand extends Command {

    public DisplayTaskCommand() { }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) {
        return message.showTaskList(taskList);
    }
}
