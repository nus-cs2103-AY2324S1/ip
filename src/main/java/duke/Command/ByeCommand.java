package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Class that handles the bye command.
 */
public class ByeCommand extends Command {
    /**
     * Executes the command.
     * @param ui the ui class used.
     * @param storage the storage that is used.
     * @param tasks the tasklist that is used.
     * @return String representation of the execution.
     */
    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) {
        return ui.printGoodByeMessage();
    }
}
