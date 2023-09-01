package Command;

import List.TaskList;
import Ui.Ui;
import FileStorage.FileStorage;
import DukeException.DukeException;
import Tasks.Todo;

/**
 * A class that is part of the command family, this in particular calls for todo tasks.
 */
public class TodoCommand extends Command{
    private String task;
    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * A method that will add a Todo task to the tasklist.
     *
     * @param tasklist contains all the past few tasks excuted.
     * @param ui contains the user interface that will be shown to the user depending on the inputs.
     * @param fileStorage Writing and reading on text files.
     * @throws DukeException If user inputs is invalid.
     */
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Todo task = new Todo(this.task);
        tasklist.add(task);
        fileStorage.write(tasklist);
        ui.showTaskAdded(task, tasklist);
    }
}
