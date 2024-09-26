package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Task;
import ui.Ui;

/**
 * The class that will execute the Unmark Task command.
 * This class extends from the Command class.
 */
public class UnmarkCommand extends Command {
    private final int index;
    /**
     * Constructs the class.
     *
     * @param index The index that will be UnMarked.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Command of UnMarking a task.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = taskList.unmarkTask(this.index);
        fileStorage.write(taskList);
        return ui.showUnMarkedTask(task);
    }
}
