public class ListCommand extends Command{
    public void execute(TaskManager taskList, Ui ui, Storage storage) throws DukeException {
        ui.showList(taskList);
    }
}
