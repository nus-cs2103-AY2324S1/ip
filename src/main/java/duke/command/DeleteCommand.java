package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to delete tasks to the task list in the Duke application.
 */
public class DeleteCommand extends Command {
    private int index;

    /**
     * Constructs an DeleteCommand object with full command and task type.
     *
     * @param index The index at which the user wish to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete task command, deleting the specified task to the task list and updating the storage.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     * @throws DukeException If there's an error while parsing the user input or updating the storage.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        assert index < 0 : "Index cannot be negative";

        if (index >= taskList.size() || index < 0) {
            throw new DukeException("OOPS!!! Invalid index to be deleted!");
        } else {
            String deletedTask = taskList.getPrint(index);
            taskList.deleteTask(index);
            ui.sendMessage("Noted. I've removed this task:\n\t\t"
                    + deletedTask
                    + "\n\tNow you have " + taskList.size() + " tasks in the list.");
            storage.updateFileContents(taskList);
        }
    }

    /**
     * Indicates whether this command is an exit command.
     * DeleteCommand is not an exit command, so this method returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
