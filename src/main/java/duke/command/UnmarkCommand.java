package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command for marking a task as undone in the task list.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * Initializes an UnmarkCommand with the index of the task to be marked as undone.
     *
     * @param index The index of the task to be marked as undone (0-based index).
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the specified task as undone in the task list.
     *
     * @param taskList The task list where the task's status will be updated.
     * @param storage  The storage component for saving tasks after updating (not used in this command).
     * @param ui       The user interface for displaying messages.
     * @return A message indicating that the task has been marked as undone in the task list.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.markTaskAsUnDone(index);
        storage.saveTasks(taskList);
        return ui.unmarkedMessage(taskList.getTask(index));
    }
}

