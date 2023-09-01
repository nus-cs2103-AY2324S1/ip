public class ByeCommand extends Command{
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.showBye();
        storage.saveTask(taskList.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
