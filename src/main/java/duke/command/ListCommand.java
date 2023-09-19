package duke.command;

import duke.main.Storage;
import duke.main.TaskList;
import duke.main.Ui;

/**
 * The ListCommand class.
 */
public class ListCommand extends Command {
    /**
     * Returns the list of tasks in the taskList.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.produceTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
