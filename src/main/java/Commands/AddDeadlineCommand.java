package Commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

public class AddDeadlineCommand implements Command {
    private Task task;

    public AddDeadlineCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.addLine(task.toString());
    }
}
