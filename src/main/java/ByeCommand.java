public class ByeCommand extends Command {
    public ByeCommand() {

    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        storage.saveAllTasks(taskList);
        ui.showGoodbyeMessage();
    }
}
