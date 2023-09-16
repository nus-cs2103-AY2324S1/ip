package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Todo;
import ui.Ui;


/**
 * The class that will execute the Todo Task command.
 * This class extends from the Command class.
 */
public class TodoCommand extends Command {
    private final String task;
    /**
     * Constructs the class.
     *
     * @param task The information of the task.
     */
    public TodoCommand(String task) {
        this.task = task;
    }

    /**
     * Executes the Command of creating an todo Task.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        Todo task = new Todo(this.task);
        taskList.add(task);
        fileStorage.write(taskList);
        return ui.showTaskAdded(task, taskList);
    }
}
