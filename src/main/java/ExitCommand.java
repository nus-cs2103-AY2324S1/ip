public class ExitCommand extends Command {

    public ExitCommand() {}

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public void execute(TaskList taskList, UserInterface ui, Storage storage) {
        ui.showFarewell();
    }
}
