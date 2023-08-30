public class ExitCommand extends Command {
    public ExitCommand() {}
    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
    }

    @Override
    protected boolean isExit() {
        return true;
    }
}
