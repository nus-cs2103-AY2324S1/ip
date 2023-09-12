package jarvis.commands;

import jarvis.exceptions.DukeException;
import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.Ui;

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
    private boolean isMark;

    /**
     * Constructs the IncorrectCommand Class.
     * @param index Index of task to be marked.
     * @param type Type of task to be marked.
     */
    public MarkCommand(int index, String type) {
        assert index > 0 : "Invalid Index!";
        this.index = index;
        this.isMark = type.equals("mark");
    }

    /**
     * Executes the required command.
     * @param tasks List of all the tasks.
     * @param ui Ui for interacting with the user.
     * @param storage Storage of the tasks.
     * @throws DukeException Throws DukeException on invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.changeStatus(this.index - 1, isMark);
        assert this.index <= tasks.size();
        storage.writeData(tasks.getAllTasks());
        return ui.showStatus(index, tasks.getTask(this.index - 1), isMark);
    }
}
