package duke.commands;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Represents the MarkCommand Class.
 * Responsible for handling mark/unmark operations.
 *
 * @author Shishir
 */
public class MarkCommand extends Command {

    /** Index of task to be marked. */
    private int index;

    /** Type of task to be marked. */
    private boolean flag;

    /**
     * Constructs the IncorrectCommand Class.
     * @param index Index of task to be marked.
     * @param type Type of task to be marked.
     */
    public MarkCommand(int index, String type) {
        this.index = index;
        this.flag = type.equals("mark");
    }

    /**
     * Executes the required command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     * @throws DukeException Throws DukeException on invalid input.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        // Check if index is invalid or the task is already marked
        if (tasks.size() <= index || tasks.getTask(index - 1).isCompleted() == flag) {
            throw new DukeException("The task you are trying to mark either doesnt exist, or is already marked");
        }

        if (flag) {
            tasks.mark(index - 1);
            ui.showMark(index, tasks.getTask(index - 1));
        } else {
            tasks.unmark(index - 1);
            ui.showUnmark(index, tasks.getTask(index - 1));
        }

        storage.writeData(tasks.getAllTasks());
    }

    /**
     * Returns the exit status of the command.
     * @return Exit status of the command.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
