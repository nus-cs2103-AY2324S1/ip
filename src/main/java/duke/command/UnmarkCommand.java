package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class UnmarkCommand extends Command {
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.markTaskAsUnDone(index);
        storage.saveTasks(taskList);
        return ui.unmarkedMessage(taskList.getTask(index));
    }
}
