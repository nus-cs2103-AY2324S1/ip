public class ByeCommand extends Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws Exception {
        ui.printBye();
        storage.writeFile(taskList);
    }
}
