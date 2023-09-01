package blip.commands;

import blip.ui.*;
import blip.tasks.*;
import blip.storage.*;
import blip.exceptions.*;

/**
 * Represents the delete command to delete tasks.
 */
public class DeleteCommand extends Command {
    /**
     * Index of the task to delete.
     */
    int index;

    /**
     * Constructor of DeleteCommand.
     * @param index The index of the task to delete
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command to delete a task.
     * @param taskList The Array List of tasks to delete task from
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
            Task taskToDelete = taskList.getTask(index);
            taskList.deleteTask(index);
            storage.saveToFile(taskList);
            ui.deletesTasksMsg(taskToDelete, taskList.size());
        } catch (WrongNumberException e) {
            ui.showInvalidTaskNumErr();
        }
    }
}
