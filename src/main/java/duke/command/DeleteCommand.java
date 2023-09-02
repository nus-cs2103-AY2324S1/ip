package duke.command;

import duke.exception.DukeNoTaskFoundException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete a task from Duke's task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the specified index.
     *
     * @param index The index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            ui.showDeleteMessage(taskList.delete(this.index), taskList.getSize());
        } catch (DukeNoTaskFoundException e) {
            ui.showError(e);
        }
    }
}