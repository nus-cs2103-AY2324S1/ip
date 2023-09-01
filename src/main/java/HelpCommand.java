public class HelpCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showCommands();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
