package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark a task as done
 */
public class MarkCommand extends Command {
    /**
     * The index of the task to be marked
     */
    private final int index;
    /**
     * The type of mark command
     */
    private final boolean isDone;

    /**
     * Constructor
     *
     * @param index the index of the task to be marked
     * @param type  the type of mark command
     */
    public MarkCommand(int index, String type) {
        assert index >= 1 : "Index cannot be less than 1";
        this.index = index;
        this.isDone = type.equals("mark");
    }

    /**
     * Executes the command
     *
     * @param tasks   the task list
     * @param ui      the ui
     * @param storage the storage
     * @return the message
     * @throws DukeException if there is an error
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check if index is invalid or the task is already marked
        if (tasks.getLength() < index || tasks.getTask(index - 1).isCompleted() == isDone) {
            throw new DukeException("The task you are trying to mark either doesnt exist, or is already marked");
        }
        tasks.changeStatus(index - 1, isDone);
        storage.writeData(tasks.getAllTasks());
        return ui.showStatus(index, tasks.getTask(index - 1), isDone);
    }

    /**
     * Returns true if the command is an exit command
     *
     * @return true if the command is an exit command
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
