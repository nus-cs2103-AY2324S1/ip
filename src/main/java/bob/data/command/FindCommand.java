package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class FindCommand extends Command {
    private String taskToFind;
    public FindCommand(String taskDescription, String taskToFind) {
        super(taskDescription);
        this.taskToFind = taskToFind;
    }

    @Override
    public void execute(Storage storage, TaskList list, Ui ui) {
        ui.print(list.find(this.taskToFind));
    }
}
