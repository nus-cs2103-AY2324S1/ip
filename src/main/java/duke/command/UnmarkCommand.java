package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as not done.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the provided task index.
     *
     * @param taskIndex The index of the task to be marked as not done.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.getTask(taskIndex);
            task.markAsNotDone();
            storage.saveData(taskList);
            return ui.showNotDone(task);
        } catch (InvalidTaskIndexException e) {
            return ui.showDukeException(e);
        } catch (IOException e) {
            return ui.showSavingError();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            return ui.showArrayIndexOutOfBoundsException();
        }
    }
}
