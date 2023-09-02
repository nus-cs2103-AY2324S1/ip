package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.exception.TaskNotFoundException;
public class MarkCommand extends Command {
    private final Integer taskIndex;
    private final boolean isMarked;
    public MarkCommand(boolean isMarked, Integer taskIndex) {
        this.isMarked = isMarked;
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex < 0 || taskIndex >= tasks.length()) {
            throw new TaskNotFoundException("Task Not Found :'(");
        }
        if (isMarked) {
            tasks.mark(taskIndex);
            ui.printMark(tasks.getTask(taskIndex));
        } else {
            tasks.unmark(taskIndex);
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
