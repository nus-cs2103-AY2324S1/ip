import java.io.IOException;

public class ToDoCommand extends Command {
    public static final String COMMAND_WORD = "todo";
    public ToDo toDo;

    public ToDoCommand(ToDo toDo) {
        this.toDo = toDo;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        tasks.add(toDo);
        ui.print(
            "Got it. I've added this task:",
            "\t" + toDo.toString(),
            "Now you have " + tasks.size() +  " tasks in the list."
        );
        storage.writeToFile(tasks);
    }
}
