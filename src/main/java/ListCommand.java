
public class ListCommand extends Command{

    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) {
        ui.showList(tasklist);
    }
}
