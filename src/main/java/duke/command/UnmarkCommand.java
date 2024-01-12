package duke.command;

import duke.exception.DukeNoTaskFoundException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark a task in Duke's task list.
 */
public class UnmarkCommand extends Command {

    private int index;

    /**
     * Constructs an UnmarkCommand with the specified index.
     *
     * @param index The index of the task to be marked as undone.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            return ui.showUnmarkMessage(taskList.markAsUndone(this.index));
        } catch (DukeNoTaskFoundException e) {
            return ui.showError(e);
        }

    }
}
