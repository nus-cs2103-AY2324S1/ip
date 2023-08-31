import java.io.IOException;

public class MarkCommand extends Command {
    protected int num;
    public MarkCommand(int num) {
        this.num = num;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        Task t = tasks.markTask(this.num);
        ui.showMarkMsg(t);
        storage.saveFiles(tasks.showList());
    }
}
