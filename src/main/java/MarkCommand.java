public class MarkCommand extends Command {

    private int markIndex;

    public MarkCommand(int markIndex) {
        this.markIndex = markIndex;
    }

    @Override
    public void execute(TaskList items, Ui ui, Storage storage) throws DukeException {
        Task item = items.mark(markIndex);
        ui.markItem(item.toString());
        storage.writeData(items.getItems());
    }
}
