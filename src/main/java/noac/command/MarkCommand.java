package noac.command;

import noac.NoacException;
import noac.Storage;
import noac.TaskList;
import noac.Ui;

public class MarkCommand  extends Command {

    private int taskIndex;
    private boolean isMark;

    public MarkCommand(int taskIndex, boolean isMark) {
        this.taskIndex = taskIndex;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {

        if (taskIndex + 1 > tasks.size() || taskIndex < 0) {
            throw new NoacException("☹ OOPS!!! Please enter a task in your list!");
        }

        if (isMark) {
            tasks.getTask(this.taskIndex).markAsDone();
        } else {
            tasks.getTask(this.taskIndex).unmarkAsDone();
        }

        ui.showMarkOrUnmark(tasks.getTask(this.taskIndex), this.isMark);
        storage.save(tasks);
    }
}
