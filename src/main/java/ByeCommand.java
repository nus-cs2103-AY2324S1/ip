public class ByeCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exitResponse();
    };
    public boolean isExit() {
        return true;
    };
}
