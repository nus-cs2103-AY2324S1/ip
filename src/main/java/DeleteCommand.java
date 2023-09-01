
public class DeleteCommand extends Command{
    private int index;
    public DeleteCommand(int index) {
        this.index = index;
    }
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        Task task = tasklist.deleteTask(this.index);
        fileStorage.write(tasklist);
        ui.showDeleteTask(task, tasklist);
    }
}
