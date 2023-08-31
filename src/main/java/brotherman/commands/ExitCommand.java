package brotherman.commands;

import brotherman.storage.Storage;
import brotherman.tasks.TaskList;
import brotherman.ui.Ui;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.saveToFile(tasks.list());
        ui.showGoodbyMessage();
    }
}
