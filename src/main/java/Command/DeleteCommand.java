package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;
import Tasks.Task;

/**
 * A class that is part of the command class, for this in particular it deletes a task.
 */
public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * A method that will delete a task from the taskList depending on the index.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = tasklist.deleteTask(this.index);
        fileStorage.write(tasklist);
        ui.showDeleteTask(task, tasklist);
    }
}
