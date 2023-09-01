package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;
import blip.exceptions.*;

/**
 * Represents the unmark command to unmark task as not done.
 */
public class UnmarkCommand extends Command {
    /**
     * Index of task to unmark as not done.
     */
    int index;

    /**
     * Constructor of UnmarkCommand.
     * @param index The index of task to mark as done
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the unmark command to unmark task as not done.
     * @param taskList The Array List of tasks to unmark a task from
     * @param ui The user interface of Blip
     * @param storage The storage for Blip
     */
    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        // Task number does not exist.
        try {
            if (this.index < 0 || this.index >= taskList.size()) {
                throw new WrongNumberException("!!! Wrong Task Number Error !!!\n");
            }
            Task taskToUnmark = taskList.getTask(index);
            taskList.unmarkTask(index);
            storage.saveToFile(taskList);
            ui.unmarksTasksMsg(taskToUnmark);
        } catch (WrongNumberException e) {
            ui.showInvalidTaskNumErr();
        }
    }
}
