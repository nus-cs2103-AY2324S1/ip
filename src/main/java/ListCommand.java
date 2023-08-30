public class ListCommand extends Command{

    public ListCommand() {
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.List();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
