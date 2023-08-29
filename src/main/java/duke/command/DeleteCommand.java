package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task task = list.remove(this.index);
        ui.printDelete(task, list.size());
    }
}
