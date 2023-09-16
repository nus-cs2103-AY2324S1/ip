package bert.commands;

import bert.storage.Storage;
import bert.tasks.TaskList;
import bert.ui.Ui;

/**
 * Represents a command that causes the program to exit.
 */
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
