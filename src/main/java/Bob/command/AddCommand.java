package Bob.command;

import Bob.enums.CommandType;

import Bob.exception.BobException;
import Bob.exception.BobInvalidCommandException;

import Bob.task.Deadline;
import Bob.task.Event;
import Bob.task.Task;
import Bob.task.TaskList;
import Bob.task.Todo;

import Bob.ui.TextUi;
import Bob.storage.StorageFile;

/**
 * The AddCommand Class encapsulates logic that can be executed
 * to add a specific task to current task list.
 */
public class AddCommand extends Command {

    CommandType command;
    private String description, startDate, endDate;

    /**
     * Constructor of the AddCommand class.
     *
     * @param command Type of AddCommand
     * @param description Text description of task
     * @param startDate Start date of task, potentially null
     * @param endDate End date of task, potentially null
     * @throws BobInvalidCommandException If CommandType is not a valid add task command
     */
    public AddCommand(CommandType command, String description, String startDate, String endDate) throws BobInvalidCommandException {
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
