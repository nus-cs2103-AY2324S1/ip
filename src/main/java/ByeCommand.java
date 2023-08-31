public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showGoodbyeMsg();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
