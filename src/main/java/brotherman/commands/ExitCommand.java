package brotherman.commands;

import brotherman.storage.*;
import brotherman.tasks.*;
import brotherman.ui.*;

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
