public class LoadCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.load(taskList);
        ui.showLoad();
    }
}
