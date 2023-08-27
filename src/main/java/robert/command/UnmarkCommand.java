package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

public class UnmarkCommand extends Command {

    private final int unmarkIndex;

    public UnmarkCommand(int unmarkIndex) {
        this.unmarkIndex = unmarkIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.unmarkTask(this.unmarkIndex);
        ui.showMessage("Ok, I've marked this task as not done yet:\n  "
                + tasks.getTask(this.unmarkIndex));
    }
}
