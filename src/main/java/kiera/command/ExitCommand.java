package kiera.command;

import kiera.Storage;
import kiera.TaskList;
import kiera.Ui;

/**
 * Command to exit application.
 */
public class ExitCommand extends Command {
    public ExitCommand() {
    }

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
