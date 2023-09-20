package commands;

import ari.Storage;
import ari.TaskList;
import ari.Ui;

import java.io.IOException;

/**
 * MarkCommand class for the command that asks to mark a task as done
 */
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
