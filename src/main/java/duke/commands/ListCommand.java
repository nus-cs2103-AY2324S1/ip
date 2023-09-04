package duke.commands;

import duke.exception.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.ui.Ui;

/**
 * A command to called to mark print the taskList.
 */
public class ListCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    /**
     * ListCommand constructor.
     *
     * @param taskList The existing taskList.
     * @param storage The storage that stores the data of the program.
     * @param ui The ui interface used to print messages.
     */
    public ListCommand(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Method that prints list.
     *
     */
    @Override
    public String execute() throws DukeException {
        return ui.printList(taskList);
    }
}
