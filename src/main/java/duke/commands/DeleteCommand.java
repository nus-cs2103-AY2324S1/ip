package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;

public class DeleteCommand extends Command {
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        ui.showDelete(tasks.taskList.get(this.taskIndex), (tasks.taskList.size() - 1));
        tasks.delete(this.taskIndex);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
