package bob.data.command;

import bob.data.task.TaskList;
import bob.storage.Storage;
import bob.ui.Ui;

public class UnmarkCommand extends Command {
    private String input;
    public UnmarkCommand(String input) {
        this.input = input;
    }
    @Override
    public String execute(Storage storage, TaskList list, Ui ui) {
        return list.setTaskIncomplete(this.input);
    }
}
