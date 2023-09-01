public class ExitCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
