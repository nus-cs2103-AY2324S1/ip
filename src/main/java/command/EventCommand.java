package command;

import dukeexception.DukeException;
import filestorage.FileStorage;
import list.TaskList;
import tasks.Event;
import ui.Ui;

/**
 * The class that will execute the Event Task command.
 * This class extends from the Command class.
 */
public class EventCommand extends Command {
    private final String task;
    private final String startDate;
    private final String endDate;

    /**
     * Constructs the class.
     *
     * @param task The task the user wants to do.
     * @param startDate The starting date.
     * @param endDate The ending date.
     */
    public EventCommand(String task, String startDate, String endDate) {
        this.task = task;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Executes the Command of creating an event Task.
     *
     * @param taskList The TaskLIst that contains all the tasks.
     * @param ui The user interface that will be shown to the user.
     * @param fileStorage The File that will be written and read from.
     * @throws DukeException If user inputs is invalid.
     */
    public String execute(TaskList taskList, Ui ui, FileStorage fileStorage) throws DukeException {
        Event task = new Event(this.task, this.startDate, this.endDate);
        taskList.add(task);
        fileStorage.write(taskList);
        return ui.showTaskAdded(task, taskList);
    }
}
