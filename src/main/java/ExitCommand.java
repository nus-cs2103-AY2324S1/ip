public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showGoodbye();
        setExit(true);
    }
}
