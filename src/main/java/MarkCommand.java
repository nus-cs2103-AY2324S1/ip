import java.io.IOException;

public class MarkCommand extends Command {
    private boolean isMark;
    private int index;
    public MarkCommand(int index, boolean isMark) {
        super(null);
        this.index = index;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, InvalidTaskNumberException {
        if (this.index >= tasks.getSize() || this.index < 0) throw new InvalidTaskNumberException();
        Task task = tasks.getTaskAtIndex(this.index);
        if (isMark) {
            task.markTask();
            ui.showMark(task);
        } else {
            task.unmarkTask();
            ui.showUnmark(task);
        }
        storage.update(tasks.getTasks());
    }
}
