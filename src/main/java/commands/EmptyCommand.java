package commands;

import data.TaskList;
import storage.Storage;
import ui.Ui;

public class EmptyCommand extends Command {
    @Override
    public void execute(
            TaskList tasks, Storage storage, Ui ui) {
        return;
    }
}
