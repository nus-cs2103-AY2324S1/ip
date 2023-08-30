package Commands;

import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class UnmarkCommand implements Command {
    public static final String UNMARK_PATTERN = "^(unmark)\\s+\\d+$";

    private int pos;

    public UnmarkCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {

    }
}
