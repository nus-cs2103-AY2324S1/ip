package command;

import filestorage.FileStorage;
import list.TaskList;
import ui.Ui;



/**
 * A class that is part of the command, for this particularly it will end the program.
 * @author LinWanLeii
 */
public class ByeCommand extends Command {

    /**
     * A method that will end the program.
     *
     * @param taskList contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     */
    public String excute(TaskList taskList, Ui ui, FileStorage fileStorage) {
        this.isExit = true;
        return ui.showGoodbye();
    }
}
