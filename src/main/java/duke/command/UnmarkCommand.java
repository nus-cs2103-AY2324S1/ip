package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to unmark tasks to the task list in the Duke application.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Constructs an UnmarkCommand object with full command and task type.
     *
     * @param index The index at which the user wish to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the unmark task command, unmarking the specific task input by the user.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        unmarkTask(taskList, ui, storage);
    }

    private void unmarkTask(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            displayInvalidUnmarkException();
        }
        unmark(taskList, ui, storage);
    }

    private void unmark(TaskList taskList, Ui ui, Storage storage) {
        taskList.unmark(index);
        ui.sendMessage("OK, I've marked this task as not done yet:\n\t\t"
                + taskList.getPrint(index));
        storage.updateFileContents(taskList);
    }

    private static void displayInvalidUnmarkException() throws DukeException {
        throw new DukeException("OOPS!!! Invalid task to be unmarked!");
    }

    /**
     * Indicates whether this command is an exit command.
     * UnmarkCommand is not an exit command, so this method returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
