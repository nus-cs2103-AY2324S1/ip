package blip.commands;

import blip.ui.BlipUI;
import blip.tasks.TaskList;
import blip.tasks.Task;
import blip.storage.BlipStorage;
import blip.exceptions.WrongNumberException;

/**
 * Represents the mark command to mark task as done.
 */
public class MarkCommand extends Command {
    /**
     * Index of task to mark as done.
     */
    int index;

    /**
     * Creates an instance of MarkCommand.
     *
     * @param index The index of task to mark as done
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the mark command to mark task as done.
     *
     * @param taskList The Array List of tasks to mark a task from
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
            Task taskToMark = taskList.getTask(index);
            taskList.markTask(index);
            storage.saveToFile(taskList);
            return ui.marksTasksMsg(taskToMark);
        } catch (WrongNumberException e) {
            return ui.showInvalidTaskNumErr();
        }
    }
}
