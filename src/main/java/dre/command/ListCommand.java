package dre.command;

import dre.storage.Storage;
import dre.ui.Ui;
import dre.exception.DreException;
import dre.task.TaskList;

public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            ui.showTasks(tasks);
        } catch (DreException e) {
            ui.showError(e.getMessage());
        }
    }
}