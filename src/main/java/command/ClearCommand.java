package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;


/**
 * The class that will execute the Clear command.
 * This class extends from the Command class.
 */
public class ClearCommand extends Command {

    /**
     * Executes the Command of clearing all the task in the TaskList.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        fileStorage.write(taskList.clear());
        return ui.showClearTask();
    }
}
