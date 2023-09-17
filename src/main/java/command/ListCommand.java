package command;

import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;


/**
 * The class that will execute the List Task command.
 * This class extends from the Command class.
 */
public class ListCommand extends Command {

    /**
     * Executes the Command of Listing all the task in the TaskList.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) {
        return ui.showList(taskList);
    }
}
