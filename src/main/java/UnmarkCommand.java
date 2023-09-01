public class UnmarkCommand extends Command{
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.unmark(index);
        ui.showUnmarkedTask(task);
        storage.save(tasks);
    }
}
