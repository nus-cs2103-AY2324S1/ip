package command;

import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;



/**
 * The class that will execute the Bye command.
 * This class extends from the Command class.
 */
public class ByeCommand extends Command {

    /**
     * Executes the Command of ending the Chat bot.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) {
        this.isExit = true;
        return ui.showGoodbye();
    }
}
