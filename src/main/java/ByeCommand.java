public class ByeCommand extends Command {
    public ByeCommand() {
    }

    @Override
    public void execute(TaskList taskList, BlipUI ui, BlipStorage storage) {
        ui.showOutro();
    }
}
