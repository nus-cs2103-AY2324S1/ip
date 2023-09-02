package arona.commands;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

public class UnMarkCommand extends Command {
    private Storage storage;
    private int taskIndex;

    public UnMarkCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
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
        taskList.getTasks().get(taskIndex).unMark();
        storage.updateTaskStatusAsUnmarked(taskIndex);
        ui.showTaskUnmarked(taskList.getTasks().get(taskIndex));
    }
}
