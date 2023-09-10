package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;
import bert.ui.Ui;

public class ExitCommand extends Command {
    public static final String COMMAND_WORD = "bye";
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
