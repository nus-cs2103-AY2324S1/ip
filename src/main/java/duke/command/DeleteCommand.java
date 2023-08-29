package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements Command{
    private String details;

    public DeleteCommand(String details) {
        this.details = details;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int deleteIndex = Integer.parseInt(this.details) - 1;
        if (deleteIndex > tasks.size() || deleteIndex < 0) {
            throw new DukeException("OOPS!! Task does not exist");
        } else {
            ui.sendMessage("Noted. I've removed this task:\n" + "\t" + tasks.get(deleteIndex).toString() + "\n"
                    + "Now you have " + Integer.toString(tasks.size()) + " tasks in the list.");
            tasks.remove(deleteIndex);
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
