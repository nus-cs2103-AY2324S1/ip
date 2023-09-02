package Commands;
import Duke.DukeException;
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
        try {
            Task removedTask = tasks.deleteTask(id);
            ui.printTaskDeletedMessage(removedTask);
        } catch (RuntimeException e) {
            throw new DukeException("\tIndex out of bounds. There are "
                                        + tasks.getSize()
                                            + " tasks currently.");
        }
    }
}
