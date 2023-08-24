import java.io.IOException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskIdx;
    public DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IllegalValueException, IOException {
        Task task = tasks.deleteTask(taskIdx);
        ui.print("OK, I've removed this task",
                "\t" + task.toString()
        );
        storage.writeToFile(tasks);
    }
}
