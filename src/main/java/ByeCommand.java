
public class ByeCommand extends Command{
    public void excute(TaskList tasklist, Ui ui, FileStorage fileStorage) {
        this.isExit = true;
        ui.showGoodbye();
    }
}
