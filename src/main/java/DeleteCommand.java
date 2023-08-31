import java.io.IOException;

public class DeleteCommand extends Command {
    protected int num;
    public DeleteCommand(int num) {
        this.num = num;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.deleteTask(num);
        ui.showDeleteMsg(t, tasks.numOfTasks());
        storage.saveFiles(tasks.showList());
    }
}
