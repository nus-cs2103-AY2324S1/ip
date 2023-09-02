package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.TaskNotFoundException;
import duke.task.Task;
public class DeleteCommand extends Command {
    private final Integer taskIndex;
    public DeleteCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.length()) {
            throw new TaskNotFoundException("Task Not Found :'(");
        }
        Task taskTemp = tasks.getTask(taskIndex);
        tasks.delete(taskIndex);
        ui.printDelete(taskTemp, tasks);
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
