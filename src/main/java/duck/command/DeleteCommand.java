package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;
import duck.task.Task;
import duck.task.TaskList;

public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index - 1;
    }

    public void execute(TaskList tasks, Ui ui,Storage storage) throws DuckException{
        Task tmpTask = tasks.getTask(index);
        tasks.delete(index);
        ui.showDeleteTaskMessage(tmpTask, tasks.getTaskCount());
        storage.deleteTask(index);
    }
}
