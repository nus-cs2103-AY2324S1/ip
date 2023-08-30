package duke.command;

import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.task);
        ui.showAddedTask(this.task, taskList.getNumberOfTasks());
        storage.save(taskList.getList(), ui);
    }
}
