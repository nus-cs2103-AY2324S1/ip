package duke.command;
import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command, saving data and exiting the program.
     *
     * @param list    The TaskList object managing the list of tasks.
     * @param ui      The Ui object to interact with the user interface.
     * @param storage The Storage object to manage data storage.
     * @throws DukeException If there is an issue executing the command.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws DukeException {
        storage.saveDataToFile(list.getList());
        return ui.showExit();
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
