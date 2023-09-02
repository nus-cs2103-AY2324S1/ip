package arona.commands;

import arona.storage.Storage;
import arona.task.TaskList;
import arona.ui.Ui;

public class MarkCommand extends Command {
    private Storage storage;
    private int taskIndex;

    public MarkCommand(TaskList taskList, Ui ui, Storage storage, int taskIndex) {
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
        taskList.getTasks().get(taskIndex).mark();
        storage.updateTaskStatusAsMarked(taskIndex);
        ui.showTaskMarkedAsDone(taskList.getTasks().get(taskIndex));
    }
}
