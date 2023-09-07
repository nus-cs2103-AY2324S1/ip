package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(index);
            ui.showUnmarkedTask(tasks.getTask(index));
        } catch (DreException e) {
            ui.showError(e.getMessage());
        }
    }
}