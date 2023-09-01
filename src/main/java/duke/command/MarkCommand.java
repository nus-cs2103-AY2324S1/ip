package duke.command;

import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        try {
            Task task = taskList.getTask(index - 1);
            task.markAsDone();
            storage.saveCurrentTasks(taskList.getTaskArray());
            ui.showTaskMarked(task);
        } catch (IndexOutOfBoundsException e) {
            throw new RichieException("No such task number");
        }

    }
}
