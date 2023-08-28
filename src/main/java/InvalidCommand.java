public class InvalidCommand extends Command{
    public void execute(TaskManager taskList, Ui ui, Storage storage) {
        ui.showInvalidCommandMessage();
    }
}
