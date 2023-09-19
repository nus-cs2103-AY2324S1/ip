package blip.commands;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.tasks.Task;
import blip.storage.BlipStorage;
import blip.exceptions.WrongNumberException;


/**
 * Represents the unmark command to unmark task as not done.
 */
public class UnmarkCommand extends Command {
    /**
     * Index of task to unmark as not done.
     */
    int index;

    /**
     * Creates an instance of UnmarkCommand.
     *
     * @param index The index of task to mark as done
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command to unmark task as not done.
     *
     * @param taskList The Array List of tasks to unmark a task from
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     * @return String message shown to user.
     */
    @Override
    public String execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        // Task number does not exist.
        try {
            if (this.index < 0 || this.index >= taskList.size()) {
                throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
            }
            Task taskToUnmark = taskList.getTask(index);
            taskList.unmarkTask(index);
            storage.saveToFile(taskList);
            return ui.unmarksTasksMsg(taskToUnmark);
        } catch (WrongNumberException e) {
            return ui.showInvalidTaskNumErr();
        }
    }
}
