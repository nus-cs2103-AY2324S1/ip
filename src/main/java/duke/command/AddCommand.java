package duke.command;

import duke.Ui;
import duke.task.TaskList;
import duke.task.Task;
import duke.Storage;
import duke.DukeException;

public class AddCommand extends Command {
    private Task taskToAdd;

    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.addTask(taskToAdd);
        ui.showAdded(taskToAdd, tasks.listSize());
        storage.saveTasks(tasks);
    }

    public boolean isExit() {
        return false;
    }
}