public class ByeCommand extends Command {
    boolean isExit = true;
    public ByeCommand() {

    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList taskList) {
        storage.saveAllTasks(taskList);
        ui.showGoodbyeMessage();
    }
}
