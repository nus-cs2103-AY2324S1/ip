import java.io.IOException;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int taskIdx;
    public UnmarkCommand(int taskIdx) {
        this.taskIdx = taskIdx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IllegalValueException, IOException {
        Task task = tasks.unmarkTask(taskIdx);
        ui.print("OK, I've marked this task as not done yet:",
                "\t" + task.toString()
        );
        storage.writeToFile(tasks);
    }
}
