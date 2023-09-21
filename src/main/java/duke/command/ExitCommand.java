package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit Duke's task management application.
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        storage.saveData(taskList);
        return ui.showExitMessage();
    }
}
