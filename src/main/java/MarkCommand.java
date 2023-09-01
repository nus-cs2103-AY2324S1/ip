
public class MarkCommand extends Command{

    private int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = tasklist.markTask(this.index);
        fileStorage.write(tasklist);
        ui.showMarkedTask(task);
    }
}
