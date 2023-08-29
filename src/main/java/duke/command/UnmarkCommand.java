package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UnmarkCommand implements Command{
    private final String details;

    public UnmarkCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int unmarkIndex = Integer.parseInt(details) - 1;
        if (unmarkIndex > tasks.size() || unmarkIndex < 0) {
            throw new DukeException("OOPS!! Task does not exist");
        }
        Task curr = tasks.get(unmarkIndex);
        curr.taskUndone();
        ui.sendMessage("OK, I've marked this task as not done yet:\n" + "\t" + curr);
        storage.editData(tasks);
    }

    @Override
    public void loadTask(TaskList tasks) {
        //Do nothing
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
