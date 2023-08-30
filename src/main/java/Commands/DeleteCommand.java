package Commands;

import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class DeleteCommand implements Command{
    public static final String DELETE_PATTERN = "^(delete)\\s+\\d+$";

    private int pos;

    public DeleteCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {

    }
}
