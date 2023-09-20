package duke.command;

import duke.exception.DukeException;
import duke.list.TaskList;
import duke.storage.Storage;
import duke.task.Task;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task as incomplete in the Duke application.
 * Extends the base Command class.
 */
public class UnmarkCommand extends Command {

    public static final String COMMAND_WORD = "unmark";

    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Task task = taskList.unmarkTask(this.index);
        storage.write(taskList);
        return ui.showUnmarkMessage(task);
    }
}