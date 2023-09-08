package command;

import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;


/**
 * A class that excutes the list command.
 */
public class ListCommand extends Command {

    /**
     * A method that will list out all the task stored in the TaskList.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     */
    public String excute(TaskList tasklist, Ui ui, FileStorage fileStorage) {
        return ui.showList(tasklist);
    }
}
