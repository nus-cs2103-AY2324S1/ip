package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

/**
 * The {@code UndoCommand} class represents a command to undo the most recent action in the task management application.
 * When executed, it removes the latest modification or task addition from the task list.
 * It provides a way to revert the last change made by the user.
 */
public class UndoCommand extends Command {

    /**
     * Executes the UndoCommand by removing the most recent action from the task list.
     * This command is typically used to revert the last change made by the user, such as adding or modifying a task.
     *
     * @param taskList The TaskList from which the most recent action should be removed.
     * @param ui       The user interface for displaying messages.
     * @param storage  The storage component for reading or writing data.
     * @return A message indicating the success of the undo operation.
     */
    @Override
    public String runCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.removeLatest();
        storage.updateFile(taskList);
        return ui.undoMessage();
    }
}
