package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the provided task index.
     *
     * @param taskIndex The index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        assert taskList != null;
        assert ui != null;
        assert storage != null;
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task removedTask = taskList.getTaskAtIndex(taskIndex);
            taskList.deleteTask(taskIndex);
            return ui.showDelete(removedTask, taskList.getLength());
        } catch (InvalidTaskIndexException e) {
            return ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return ui.showArrayIndexOutOfBoundsException();
        }
    }
}
