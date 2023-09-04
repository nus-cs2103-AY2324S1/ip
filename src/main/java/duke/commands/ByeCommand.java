package duke.commands;

import duke.exception.DukeException;
import duke.utilities.Storage;
import duke.utilities.TaskList;
import duke.ui.Ui;

/**
 * A command to called to say goodbye.
 */
public class ByeCommand extends Command {
    protected TaskList taskList;
    protected Storage storage;
    protected Ui ui;

    /**
     * ByeCommand constructor
     *
     * @param taskList The existing taskList.
     * @param storage The storage that stores the data of the program.
     * @param ui The ui interface used to print messages.
     */
    public ByeCommand(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Method that prints goodbye.
     *
     */
    @Override
    public String execute() throws DukeException {
        try {
            storage.save(taskList);
        } catch (Exception e) {
            ui.printError(e.getMessage());
        }
        return ui.printGoodBye();
    }
}
