package duke.command;

import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to list all existing tasks.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.getListMessage(tasks.getAllTasks());
    }
}
