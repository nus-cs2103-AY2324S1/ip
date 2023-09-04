package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.utilities.Ui;

/**
 * A command to called to mark a task undone from the taskList.
 */
public class UnMarkCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected int index;

    /**
     * UnMark Command constructor.
     *
     * @param taskList The existing taskList.
     * @param storage The storage that stores the data of the program.
     * @param ui The ui interface used to print messages.
     * @param index The index of task to update.
     */
    public UnMarkCommand(TaskList taskList, Storage storage, Ui ui, int index) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.index = index;
    }

    /**
     * Method that marks task as undone.
     *
     */
    @Override
    public void execute() throws DukeException {
        ui.printMarkedUnDone(taskList, index - 1);
        taskList.markAsDone(index - 1);
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
    }
}
