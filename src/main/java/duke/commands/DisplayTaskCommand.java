package duke.commands;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;
import duke.data.Message;

/**
 * The DisplayTaskCommand display the task requested when it is executed.
 */
public class DisplayTaskCommand extends Command {

    public DisplayTaskCommand() { }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws DukeException {
        return message.showTaskList(taskList);
    }
}
