public class ExitCommand extends Command {
    public ExitCommand() {
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showBye();
    }

    @Override
    boolean isExit() {
        return true;
    }

}
