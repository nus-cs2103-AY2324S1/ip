package duke.Command;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.io.IOException;

public class DeleteCommand extends Command  {
    private Task element;
    private int index;
    public DeleteCommand(Task element, int index) {
        this.element = element;
        this.index = index;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        tasks.deleteTask(index);
        storage.writeTasksToFile(tasks);
        ui.printDeleteTasks(tasks, element);
    }

    @Override
    public boolean isExit() {
        return false;
    }


}
