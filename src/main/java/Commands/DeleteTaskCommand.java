package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
public class DeleteTaskCommand implements Command {
    int id;
    public DeleteTaskCommand(int id) {
        this.id = id;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task removedTask = tasks.deleteTask(id);
        ui.printTaskDeletedMessage(removedTask);
    }
}
