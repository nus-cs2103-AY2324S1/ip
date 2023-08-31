import java.io.IOException;

public class AddToDoCommand extends Command {
    private final String description;

    public AddToDoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        Todo newTodo = new Todo(description);
        taskList.addTask(newTodo);
        Storage.save(newTodo);
        ui.showTaskAdded(newTodo, taskList.countTasks());
    }
}
