package kevin.commands;

import kevin.storage.Storage;
import kevin.task.TaskList;
import kevin.ui.Ui;

/**
 * Represents an exit command in the Duke application.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showExit();
    }

    @Override
    public String executeGui(TaskList tasks, Ui ui, Storage storage) {
        return ui.showExitGui();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
