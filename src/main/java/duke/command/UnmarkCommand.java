package duke.command;

import duke.Storage;
import duke.Ui;
import duke.exception.InvalidTaskIndexException;
import duke.task.Task;
import duke.task.TaskList;
import java.io.IOException;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(int taskIndex){
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            if (taskIndex < 0 || taskIndex >= taskList.getLength()) {
                throw new InvalidTaskIndexException(taskIndex + 1);
            }

            Task task = taskList.get(taskIndex);
            task.markAsNotDone();
            ui.showNotDone(task);
            storage.saveData(taskList);
        } catch (InvalidTaskIndexException e) {
            ui.showDukeException(e);
        } catch (IOException e) {
            ui.showSavingError();
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            ui.showArrayIndexOutOfBoundsException();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
