package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class FindCommand extends Command {
    private String taskToFind;
    public FindCommand(String taskToFind) {
        super();
        this.taskToFind = taskToFind;
    }

    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return list.find(this.taskToFind);
    }
}
