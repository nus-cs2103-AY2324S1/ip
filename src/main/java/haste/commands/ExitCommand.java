package haste.commands;

import haste.data.Storage;
import haste.data.TaskList;
import haste.ui.Ui;

public class ExitCommand extends Command {
    private Storage store;
    public ExitCommand(Storage store) {
        this.store = store;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        ui.bye();
        this.store.delete();
        this.store.save(tasks);
    }
}
