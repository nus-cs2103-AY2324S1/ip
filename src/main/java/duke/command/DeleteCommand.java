package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task removedTask = taskList.get(taskIndex);
            taskList.delete(taskIndex);
            ui.showDelete(removedTask, taskList.getLength());

        } catch (InvalidTaskIndexException e) {
            ui.showDukeException(e);
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            ui.showArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
