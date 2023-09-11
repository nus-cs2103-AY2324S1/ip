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
        deleteTask(taskList, ui, storage);
    }

    private void deleteTask(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            displayInvalidDeleteException();
        }
        delete(taskList, ui, storage);
    }

    private static void displayInvalidDeleteException() throws DukeException {
        throw new DukeException("OOPS!!! Invalid index to be deleted!");
    }

    private void delete(TaskList taskList, Ui ui, Storage storage) {
        String deletedTask = taskList.getPrint(index);
        taskList.deleteTask(index);
        displayDeletedTaskMessage(taskList, ui, deletedTask);
        storage.updateFileContents(taskList);
    }

    private static void displayDeletedTaskMessage(TaskList taskList, Ui ui, String deletedTask) {
        ui.sendMessage("Noted. I've removed this task:\n\t\t"
                + deletedTask
                + "\n\tNow you have " + taskList.size() + " tasks in the list.");
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
