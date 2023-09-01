
public class UnmarkCommand extends Command{
    private int index;
    public UnmarkCommand(int index) {
        this.index = index;
    }
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = tasklist.unmarkTask(this.index);
        fileStorage.write(tasklist);
        ui.showUnMarkedTask(task);
    }
}
