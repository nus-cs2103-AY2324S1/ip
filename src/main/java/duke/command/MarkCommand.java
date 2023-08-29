package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.getTask(taskIndex);
            task.markAsDone();
            ui.showDone(task);
            storage.saveData(taskList);
        } catch (InvalidTaskIndexException e) {
            ui.showDukeException(e);
        } catch (IOException e) {
            ui.showSavingError();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
