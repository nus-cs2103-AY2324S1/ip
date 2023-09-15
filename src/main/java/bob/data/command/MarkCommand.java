package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class MarkCommand extends Command {
    private int taskIndex;
    public FindCommand(String taskDescription, int taskIndex) {
        super(taskDescription);
        this.taskToFind = taskToFind;
    }

    @Override
    public void execute(Storage storage, TaskList list, Ui ui) {
        ui.print(list.find(this.taskToFind));
    }
}
