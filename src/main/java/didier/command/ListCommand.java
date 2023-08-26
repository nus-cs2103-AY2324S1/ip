package didier.command;

import didier.Storage;
import didier.UI;
import didier.exception.TaskNumberException;
import didier.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws TaskNumberException {
        ui.botPrintTaskList(taskList);
    }
}
