package duke.command;

import duke.storage.Storage;
import duke.task.*;
import duke.ui.Ui;
import duke.exception.DukeException;

public class UnmarkCommand extends Command{
    private int index;
    private boolean isExit = false;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if (this.index + 1 > tasks.size()) {
            throw new DukeException(ui.messageCard("The current number of tasks is " + tasks.size() + ", " +
                    "so you can't unmark task " + (index + 1) + "!!."));
        }

        //update
        Task task = tasks.get(index);
        tasks.unmarkTask(index);
        storage.updateFile(tasks);

        String res = "OK, I've marked this task as not done yet:" + "\n"
                + "[" + task.getStatusIcon() + "] " + task.getDescription();
        ui.printMessage(res);
    }
    public boolean isExit() {
        return this.isExit;
    }
}
