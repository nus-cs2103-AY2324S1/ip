package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if(index >= 0 && index < tasks.getLength()) {
            ui.deletePrint(tasks.getTask(index), tasks.getTaskCount() - 1);
            tasks.remove(index);
        }
    }
}