package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents the Command Bye that ends interaction with the user.
 *
 * @author Joseph Oliver Lim
 */
public class ByeCommand extends Command {

    /**
     * Executes the ByeCommand.
     *
     * @param tasks The TaskList where the command is to be executed.
     * @param ui The Ui that functions as user interface.
     * @param storage The Storage that functions to store data.
     * @return A String to be shown to the user after the command is executed.
     * @throws DukeException If failed to write data into file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        storage.writeToFile(tasks);
        return ui.bye();
    }
}
