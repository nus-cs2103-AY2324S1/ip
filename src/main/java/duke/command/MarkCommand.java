package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(index);
        ui.showMarkMessage(taskList.getTask(index));
        storage.saveListToDisk(taskList.getTasks());
    }
}
