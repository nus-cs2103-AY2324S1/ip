public class ByeCommand extends Command {
    public ByeCommand() {
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showOutroMessage();
    }
}
