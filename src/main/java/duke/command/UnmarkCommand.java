package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("OOPS!!! Invalid task to be unmarked!");
        } else {
            taskList.unmark(index);
            ui.sendMessage("OK, I've marked this task as not done yet:\n\t\t"
                    + taskList.getPrint(index));
            storage.updateFileContents(taskList);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
