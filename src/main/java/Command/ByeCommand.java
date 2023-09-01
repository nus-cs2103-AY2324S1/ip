package Command;
import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;

/**
 * A class that is part of the command, for this particularly it will end the program.
 * @author LinWanLeii
 */
public class ByeCommand extends Command{

    /**
     * A method that will end the program.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     */
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) {
        this.isExit = true;
        ui.showGoodbye();
    }
}
