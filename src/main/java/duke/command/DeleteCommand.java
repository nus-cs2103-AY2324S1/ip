package duke.command;

import duke.RichieException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) throws RichieException {
        try {
            Task task = taskList.getTask(index - 1);
            taskList.remove(this.index);
            storage.saveCurrentTasks(taskList.getTaskArray());
            int listSize = taskList.getSize();
            ui.showMessage("Noted. I've removed this task:\n" + task + "\nNow you have "
                    + listSize + " tasks in the list.");
        } catch (IndexOutOfBoundsException e) {
            throw new RichieException("No such task number");
        }
    }
}
