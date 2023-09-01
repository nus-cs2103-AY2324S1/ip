
public class ClearCommand extends Command{
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) throws DukeException {
        fileStorage.write(tasklist.clear());
        ui.showClearTask();
    }
}
