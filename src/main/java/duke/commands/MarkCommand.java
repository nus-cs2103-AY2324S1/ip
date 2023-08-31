package duke.commands;

import duke.exceptions.DukeException;
import duke.tasks.DukeList;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a command to mark or unmark a task as done.
 */
public class MarkCommand extends Command {
    private int index;
    private boolean isDone;

    /**
     * Constructs a command to mark or unmark a task as done.
     *
     * @param index The index of the task to be marked or unmarked.
     * @param type  The type of action: "mark" or "unmark".
     */
    public MarkCommand(int index, String type) {
        this.index = index;
        this.isDone = type.equals("mark");
    }

    /**
     * Executes the command to mark or unmark a task as done.
     *
     * @param tasks   The list of tasks.
     * @param ui      The user interface.
     * @param storage The storage manager.
     * @throws DukeException If the specified index is out of bounds or the task is already marked/unmarked.
     */
    @Override
    public void execute(DukeList tasks, Ui ui, Storage storage) throws DukeException {
        if (tasks.getSize() < index || tasks.getTask(index - 1).isDone() == isDone) {
            throw new DukeException("The task you are trying to mark is either out of bound, " +
                    "or has already been marked/unmarked");
        }

        if (isDone) {
            tasks.setTaskAsDone(index);
            ui.acknowledgeMark(index, tasks.getTask(index - 1));
        } else {
            tasks.setTaskAsUndone(index);
            ui.acknowledgeUnmark(index, tasks.getTask(index - 1));
        }

        storage.updateStorage(tasks.getArrayList());
    }

    /**
     * Checks if the command is an exit command.
     *
     * @return False, as this command is not an exit command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
