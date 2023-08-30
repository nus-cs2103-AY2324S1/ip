package Commands;

import Storage.Storage;
import TaskList.TaskList;
import Ui.Ui;

public class MarkCommand implements Command {
    private int pos;

    public MarkCommand(int pos) {
        this.pos = pos;
    }

    public static final String MARK_PATTERN = "^(mark)\\s+\\d+$";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (pos > tasks.size() || pos <= 0) {
            System.out.println("Invalid index. Please enter again.");
        } else {
            tasks.mark(pos);
            Storage.refresh(tasks);
        }
    }
}
