package duke.commands;

import duke.DataStorage;
import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, DataStorage store) throws DukeException {
        tasks.add(task);
        ui.showAdd(task, tasks.taskList.size());
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
