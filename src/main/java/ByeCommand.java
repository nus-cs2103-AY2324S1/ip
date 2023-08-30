public class ByeCommand extends Command{

    public ByeCommand(){
    }

    @Override
    public void execute(TaskList t, Ui ui, FileHandler f) {
        ui.bye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
