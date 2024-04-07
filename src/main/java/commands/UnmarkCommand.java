package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a task as done in the task list.
 */
public class UnmarkCommand extends Command {

    private int taskNum;

    /**
     * Initializes a new instance of the UnmarkCommand class.
     *
     * @param taskNum The index of the task to be unmarked as done.
     */
    public UnmarkCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    /**
     * Executes the UnmarkCommand, marking the specified task as undone.
     *
     * @param taskList The list of tasks.
     * @param ui       The user interface to display messages.
     * @param storage  The storage to save the task list.
     * @return A message indicating the task was successfully unmarked.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsUndone(taskNum);
        return ui.showUnmarkTask(taskList.getTask(taskNum));
    }
}
