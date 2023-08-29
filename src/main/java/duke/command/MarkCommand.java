package duke.command;

import duke.task.Task;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task task = list.mark(this.index);
        ui.printDelete(task, list.size());
    }
}
