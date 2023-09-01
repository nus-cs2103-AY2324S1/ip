public class ExitCommand extends Command{

    private boolean isExit = true;

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoacException {
        ui.showByeMessage();
    }

    @Override
    public boolean isExit() {
        return isExit;
    }
}
