package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmarkTask(index);
        ui.showUnmarkMessage(taskList.getTask(index));
        storage.saveListToDisk(taskList.getTasks());
    }
}
