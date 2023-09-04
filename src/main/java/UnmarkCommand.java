public class UnmarkCommand extends Command {
    int index;
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.unmark(this.index);
            ui.taskUnmark(tasks.get(this.index));
        } catch (IndexOutOfBoundsException e) {
            ui.showError(e.getMessage());
        }
        storage.saveTaskList(tasks);
    }
}
