package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.ui.Ui;
import duke.utilities.Storage;
import duke.utilities.TaskList;

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
    public String execute() throws DukeException {
        String res = "";
        try {
            res = ui.printMarkedUnDone(taskList, index - 1);
            taskList.markAsNotDone(index - 1);
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        } catch (IndexOutOfBoundsException e) {
            res = ui.printError("I do not understand. You do not have that task.");
        }
        return res;
    }
}
