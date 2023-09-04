package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a command to mark tasks to the task list in the Duke application.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Constructs an MarkCommand object with full command and task type.
     *
     * @param index The index at which the user wish to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }


    /**
     * Executes the mark task command, marking the specific task input by the user.
     *
     * @param taskList The list of tasks.
     * @param ui The user interface to interact with the user.
     * @param storage The storage to update the tasks in the file.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        if (index >= taskList.size() || index < 0) {
            throw new DukeException("OOPS!!! Invalid task to be marked!");
        } else {
            taskList.mark(index);
            ui.sendMessage("OK, I've marked this task as done yet:\n\t\t"
                    + taskList.getPrint(index));
            storage.updateFileContents(taskList);
        }
    }

    /**
     * Indicates whether this command is an exit command.
     * MarkCommand is not an exit command, so this method returns false.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
