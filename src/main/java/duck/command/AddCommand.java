package duck.command;

import duck.DuckException;
import duck.Storage;
import duck.Ui;

import duck.task.Task;
import duck.task.TaskList;

public class AddCommand extends Command {
    private Task newTask;

    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(TaskList tasks, Ui ui,Storage storage) throws DuckException{
        tasks.add(newTask);
        ui.showAddTaskMessage(newTask, tasks.getTaskCount());
        storage.addTask(newTask);
    }
}
