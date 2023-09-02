package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private Storage storage;
    private int taskIndex;

    public DeleteCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
        super(taskList, ui);
        this.storage = storage;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() {
        if (taskIndex < 0 || taskIndex >= taskList.getTasks().size()) {
            ui.showTaskDoesNotExist(taskIndex);
            return;
        }
        Task task = taskList.getTasks().get(taskIndex);
        taskList.getTasks().remove(taskIndex);
        storage.deleteTask(taskIndex);
        ui.showTaskRemoved(task, taskList.getTasks().size());
    }
}
