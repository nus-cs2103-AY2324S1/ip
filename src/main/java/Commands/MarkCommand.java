package Commands;

import Storage.Storage;
import TaskList.TaskList;
import UI.UI;

public class MarkCommand implements Command {
    private int pos;

    public MarkCommand(int pos) {
        this.pos = pos;
    }
    public static final String MARK_PATTERN = "^(mark)\\s+\\d+$";

    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {

    }
}
