public class ListCommand extends Command {

    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        ui.list(items.getItems());
    }
}
