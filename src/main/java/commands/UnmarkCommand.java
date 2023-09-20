package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

/**
 * UnmarkCommand class for command that asks to unmark a task as done.
 */
public class UnmarkCommand extends Command {
    private int indexToUnmark;

    public UnmarkCommand(String index) {
        this.indexToUnmark = java.lang.Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.changeStatusOfTask(indexToUnmark);
        storage.updateFileAfterMark(indexToUnmark + 1);
        return ui.printAfterUnmark(indexToUnmark, tasks);
    }
}
