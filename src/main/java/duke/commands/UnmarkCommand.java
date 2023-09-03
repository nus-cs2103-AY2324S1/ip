package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class UnmarkCommand extends Command {

    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.unmark(this.taskIndex);
        ui.showUnmark(tasks.getTask(this.taskIndex));
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
