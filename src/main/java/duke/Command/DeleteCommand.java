package duke.Command;

import java.io.IOException;

import duke.Exception.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;


public class DeleteCommand extends Command {
    private Task element;
    private int index;
    public DeleteCommand(Task element, int index) {
        this.element = element;
        this.index = index;
    }

    @Override
    public String execute(Ui ui, Storage storage, TaskList tasks) throws DukeException, IOException {
        tasks.deleteTask(index);
        storage.writeTasksToFile(tasks);
        return ui.printDeleteTasks(tasks, element);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
