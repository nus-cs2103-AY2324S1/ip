public class ExitCommand extends Command{
    private boolean isExit = true;
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.bye();
    }
    @Override
    public boolean isExit() {
        return this.isExit;
    }
}
