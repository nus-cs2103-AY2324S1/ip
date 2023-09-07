package duke.commands;

import duke.data.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The DisplayTaskCommand display the task requested when it is executed.
 */
public class DisplayTaskCommand extends Command {

    public DisplayTaskCommand() { }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showTaskList(taskList);
    }
}
