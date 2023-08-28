public class ExitCommand extends Command {
    
    @Override
    void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.printExitMessage();
    }

    @Override
    boolean isExit() {
        return true;
    }
}
