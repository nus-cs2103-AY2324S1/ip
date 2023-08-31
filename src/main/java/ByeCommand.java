public class ByeCommand extends Command {

    public ByeCommand() {

    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
