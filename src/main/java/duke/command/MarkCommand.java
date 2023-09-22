package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

public class MarkCommand extends Command {
    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.markTaskAsDone(index);
        storage.saveTasks(taskList);
        return ui.markedMessage(taskList.getTask(index));
    }
}
