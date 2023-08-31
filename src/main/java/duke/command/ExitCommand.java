package duke.command;
import duke.ui.Ui;
import duke.task.*;
import duke.storage.Storage;
import duke.DukeException;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, saving data and displaying an exit message.
     *
     * @param list    The TaskList object managing the list of tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to manage data storage.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        storage.saveDataToFile(list.getList());
        ui.showExit();
    }

    /**
     * Indicates whether this command is an exit command.
     *
     * @return True, as this command is an exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
