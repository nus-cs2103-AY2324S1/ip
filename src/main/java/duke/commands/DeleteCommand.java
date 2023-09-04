package duke.commands;

import java.io.IOException;

import duke.exception.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.ui.Ui;

/**
 * A command to called to delete a task from the taskList.
 */
public class DeleteCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;
    protected int index;

    /**
     * Delete command constructor to delete a task.
     *
     * @param taskList The existing taskList.
     * @param storage The storage that stores the data of the program.
     * @param ui The ui interface used to print messages.
     * @param index The index of task to delete.
     */
    public DeleteCommand(TaskList taskList, Storage storage, Ui ui, int index) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
        this.index = index;
    }

    /**
     * Method that deletes task from list.
     *
     */
    @Override
    public String execute() throws DukeException {
        String res = ui.printDeleteTask(taskList, index);
        taskList.deleteTask(index);
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }
        return res;
    }
}
