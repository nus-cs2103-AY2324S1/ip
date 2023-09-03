package bob.command;

import bob.enums.CommandType;
import bob.exception.BobException;
import bob.exception.BobInvalidCommandException;
import bob.storage.StorageFile;
import bob.task.Deadline;
import bob.task.Event;
import bob.task.Task;
import bob.task.TaskList;
import bob.task.Todo;
import bob.ui.TextUi;


/**
 * The AddCommand Class encapsulates logic that can be executed
 * to add a specific task to current task list.
 */
public class AddCommand extends Command {

    private CommandType command;
    private String description;
    private String startDate;
    private String endDate;

    /**
     * Constructor of the AddCommand class.
     *
     * @param command Type of AddCommand
     * @param description Text description of task
     * @param startDate Start date of task, potentially null
     * @param endDate End date of task, potentially null
     * @throws BobInvalidCommandException If CommandType
     *         is not a valid add task command
     */
    public AddCommand(CommandType command, String description,
                      String startDate, String endDate) throws BobInvalidCommandException {
        if (command != CommandType.TODO && command != CommandType.DEADLINE && command != CommandType.EVENT) {
            throw new BobInvalidCommandException("You can only add tasks of type: Todo, Deadline and Event");
        }
        this.command = command;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    @Override
    public void execute(TaskList taskList, StorageFile storageFile, TextUi ui) throws BobException {
        Task task = null;
        switch (command) {
        case TODO:
            task = new Todo((this.description));
            break;
        case DEADLINE:
            task = new Deadline(this.description, this.endDate);
            break;
        case EVENT:
            task = new Event(this.description, this.startDate, this.endDate);
            break;
        default:
        }
        taskList.addTask(task);
        assert task != null;
        ui.printAddMessage(task);
        storageFile.saveTasks(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
