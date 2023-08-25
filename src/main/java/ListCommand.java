public class ListCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showList();
        ui.showMessage(tasks.toString());
    }
}
