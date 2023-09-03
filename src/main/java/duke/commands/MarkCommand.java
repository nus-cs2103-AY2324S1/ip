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
    private boolean isMark;

    /**
     * Constructs the IncorrectCommand Class.
     * @param index Index of task to be marked.
     * @param type Type of task to be marked.
     */
    public MarkCommand(int index, String type) {
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
//        ui.showLine();
        tasks.changeStatus(index - 1, isMark);
//        ui.showStatus(index, tasks.getTask(index - 1), isMark);
//        ui.showLine();
        storage.writeData(tasks.getAllTasks());
        return ui.showStatus(index,  tasks.getTask(index - 1), isMark);
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
