package duke.commands;

import duke.data.Message;
import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.storage.Storage;

/**
 * Represents a command for displaying the task list when executed.
 */
public class DisplayTaskCommand extends Command {

    public DisplayTaskCommand() { }

    @Override
    public String execute(TaskList taskList, Message message, Storage storage) throws DukeException {
        return message.showTaskList(taskList);
    }
}
