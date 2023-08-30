package Commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

public class DeleteCommand implements Command{
    public static final String DELETE_PATTERN = "^(delete)\\s+\\d+$";

    private int pos;

    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.delete(pos);
    }
}
