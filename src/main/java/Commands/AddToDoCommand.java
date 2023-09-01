package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
import Tasks.ToDo;
public class AddToDoCommand implements Command {
    private ToDo toDo;
    public AddToDoCommand(String name) {
        this.toDo = new ToDo(name, false);
    }

    public ToDo getTodo() {
        return this.toDo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.toDo);
        ui.printTaskAddedMessage(toDo, tasks);
    }
}
