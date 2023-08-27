package robert.command;

import robert.exception.RobertException;
import robert.storage.Storage;
import robert.task.TaskList;
import robert.ui.Ui;

public class MarkCommand extends Command {

    private final int markIndex;

    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws RobertException {
        tasks.markTask(this.markIndex);
        ui.showMessage("Nice! I've marked this task as done:\n  "
                + tasks.getTask(this.markIndex));
    }
}
