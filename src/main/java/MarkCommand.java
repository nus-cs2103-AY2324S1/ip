public class MarkCommand extends Command {
    private Integer index;
    public MarkCommand(Integer index) {
        this.index = index;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.displayMessage(tasks.mark(index));
        storage.save(tasks);
    }
}
