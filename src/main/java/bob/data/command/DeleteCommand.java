package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class DeleteCommand extends Command {
    private String input;
    public DeleteCommand(String input) {
        super();
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return list.deleteTask(this.input);
    }
}
