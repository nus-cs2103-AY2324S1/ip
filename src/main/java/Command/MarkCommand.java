package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;
import Tasks.Task;

/**
 * A class that will mark the task as done.
 */
public class MarkCommand extends Command{

    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * A method that will excute the mark command depending on the index by user .
     *
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = tasklist.markTask(this.index);
        fileStorage.write(tasklist);
        ui.showMarkedTask(task);
    }
}
