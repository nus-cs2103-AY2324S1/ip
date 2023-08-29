package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand implements Command{
    private final String details;

    public MarkCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int markIndex = Integer.parseInt(details) - 1;
        if (markIndex > tasks.size() || markIndex < 0) {
            throw new DukeException("OOPS!! Task does not exist");
        } else {
            Task curr = tasks.get(markIndex);
            curr.taskDone();
            ui.sendMessage("Nice! I've marked this task as done:\n" + "\t" + curr);
            storage.editData(tasks);
        }
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
