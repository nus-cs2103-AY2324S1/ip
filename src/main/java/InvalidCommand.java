public class InvalidCommand extends Command{
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showInvalidCommandMessage();
    }
}
