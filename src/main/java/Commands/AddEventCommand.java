package Commands;

import storage.Storage;
import tasklist.TaskList;
import tasks.Task;
import ui.Ui;

public class AddEventCommand implements Command {
    private Task task;

    public AddEventCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(task);
        storage.addLine(task.toString());
    }
}
