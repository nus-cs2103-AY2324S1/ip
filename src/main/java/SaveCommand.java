public class SaveCommand extends Command{
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        storage.save(taskList);
        ui.showSave();
    }
}
