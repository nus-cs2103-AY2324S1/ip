package didier.command;

import didier.Storage;
import didier.UI;
import didier.exception.TaskNumberException;
import didier.TaskList;

/**
 * The ListCommand encapsulates the logic of what occurs when the user tries to list all the tasks in
 * the current task list.
 */
public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws TaskNumberException {
        ui.botPrintTaskList(taskList);
    }
}
