import java.io.IOException;

public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int taskIdx;
    public MarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IllegalValueException, IOException {
        Task task = tasks.markTask(taskIdx);
        ui.print("Nice! I've marked this task as done:",
                "\t" + task.toString()
        );
        storage.writeToFile(tasks);
    }
}
