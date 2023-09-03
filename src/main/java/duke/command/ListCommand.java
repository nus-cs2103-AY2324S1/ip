package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a command to list all tasks in the task list.
 */
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
