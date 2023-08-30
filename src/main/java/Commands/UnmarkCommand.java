package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class UnmarkCommand implements Command {
    public static final String UNMARK_PATTERN = "^(unmark)\\s+\\d+$";

    private int pos;

    public UnmarkCommand(int pos) {
        this.pos = pos;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {

    }
}
