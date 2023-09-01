public class ExitCommand extends Command {
    @Override
    public boolean isExit() {
        return true;
    }
    @Override
    public void execute(TaskList tasksList, Ui ui, Storage storage) {
        storage.saveToFile(tasksList.retrieveList());
        ui.showGoodbye();
    }
}
