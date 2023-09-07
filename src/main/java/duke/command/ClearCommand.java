package duke.command;

import duke.data.exception.DukeException;
import duke.data.task.TaskList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to clear the task list.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.clear();
        storage.save(tasks);
        return ui.getMessage("Got it. I've cleared all tasks.");
    }
}
