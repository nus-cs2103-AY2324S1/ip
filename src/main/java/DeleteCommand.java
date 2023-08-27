public class DeleteCommand extends Command {

    private int deleteNumber;

    public DeleteCommand(int deleteNumber) {
        this.deleteNumber = deleteNumber;
    }

    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.delete(deleteNumber);
        ui.deleteItem(item.toString(), items.getCount());
        storage.writeData(items.getItems());
    }
}
