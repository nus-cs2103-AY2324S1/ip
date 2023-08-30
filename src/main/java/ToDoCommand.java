import java.util.ArrayList;

public class ToDoCommand extends Command {

    private String description;

    public ToDoCommand(String description) {
        super(CommandType.TODO);
        this.description = description;
    }

    public void execute(ArrayList<Task> tasks, Ui ui) {
        Task task = new ToDo(description);
        tasks.add(task);
        ui.todoMessage(task);
        ui.taskListSizeMessage(tasks.size(), true);
    }
}
