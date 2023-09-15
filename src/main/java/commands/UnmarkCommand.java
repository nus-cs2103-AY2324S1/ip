package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

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
