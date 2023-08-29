import java.io.IOException;

public class DeleteCommand extends Command {
    public static final String COMMAND_WORD = "delete";
    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        super();
        this.taskNumber = taskNumber;
    }

    public DeleteCommand() {
    }

    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DukeException, IOException {
        taskList.markTask(taskNumber);
        storage.write(taskList.getTasks());
        ui.showMarkedTask(taskList.getTasks().get(taskNumber));
    }
}
