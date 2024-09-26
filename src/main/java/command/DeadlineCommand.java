package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Deadline;
import ui.Ui;

/**
 * The class that will execute the Deadline Task command.
 * This class extends from the Command class.
 */
public class DeadlineCommand extends Command {
    private final String task;
    private final String deadDate;

    /**
     * Constructs the class.
     *
     * @param task The task that is input by the user.
     * @param deadDate The deadline of the task.
     */
    public DeadlineCommand(String task, String deadDate) {
        this.task = task;
        this.deadDate = deadDate;
    }

    /**
     * Executes the Command of creating a deadline task.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        Deadline task = new Deadline(this.task, this.deadDate);
        taskList.add(task);
        fileStorage.write(taskList);
        return ui.showTaskAdded(task, taskList);
    }
}
