package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

import java.io.IOException;

public class MarkCommand extends Command {

    private int indexToMark;


    public MarkCommand(String index) {
        this.indexToMark = java.lang.Integer.parseInt(index) - 1;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.changeStatusOfTask(indexToMark);
        storage.updateFileAfterMark(indexToMark + 1);
        return ui.printAfterMark(indexToMark, tasks);
    }
}
