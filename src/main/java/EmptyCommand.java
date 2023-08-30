public class EmptyCommand extends Command{

    public EmptyCommand(){
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.empty();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}