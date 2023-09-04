package duke.command;

import java.io.IOException;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructs a MarkCommand with the provided task index.
     *
     * @param taskIndex The index of the task to be marked as done.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.getTask(taskIndex);
            task.markAsDone();
            storage.saveData(taskList);
            return ui.showDone(task);
        } catch (InvalidTaskIndexException e) {
            return ui.showDukeException(e);
        } catch (IOException e) {
            return ui.showSavingError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
