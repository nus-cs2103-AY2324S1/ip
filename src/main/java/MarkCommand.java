public class MarkCommand extends Command{
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.mark(index);
        ui.showMarkedTask(task);
        storage.save(tasks);
    }
}
