package Commands;
import OOP.TaskList;
import OOP.Ui;
import OOP.Storage;
import Tasks.Task;
import Tasks.ToDo;
public class AddToDoCommand implements Command {
    private String name;
    public AddToDoCommand(String name) {
        this.name = name;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task toDo= new ToDo(name, false);
        tasks.addTask(toDo);
        ui.printTaskAddedMessage(toDo, tasks);
    }
}
