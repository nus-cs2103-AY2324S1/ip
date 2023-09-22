package duke.command;

import duke.TaskList;
import duke.Storage;
import duke.Ui;

/**
 * Represents a command for marking a task as done.
 */
public class MarkCommand extends Command {
    private int index;

    /**
     * Initializes a MarkCommand with the index of the task to mark as done.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to mark the specified task as done.
     *
     * @param taskList The task list containing tasks.
     * @param storage  The storage component for saving tasks after marking (not used in this command).
     * @param ui       The user interface for displaying messages.
     * @return A message indicating that the task has been marked as done.
     */
    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        taskList.markTaskAsDone(index);
        storage.saveTasks(taskList);
        return ui.markedMessage(taskList.getTask(index));
    }
}

