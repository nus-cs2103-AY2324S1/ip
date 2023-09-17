package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Task;
import ui.Ui;


/**
 * The class that will execute the Mark Task command.
 * This class extends from the Command class.
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs the class.
     *
     * @param index The index that will be marked.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the Command of Marking a task as done.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = taskList.markTask(this.index);
        fileStorage.write(taskList);
        return ui.showMarkedTask(task);
    }
}
