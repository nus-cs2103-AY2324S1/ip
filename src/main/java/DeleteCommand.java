public class DeleteCommand extends Command {
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(index);
        ui.showDeletedTask(task);
        ui.showNumberOfTasks(tasks);
        storage.save(tasks);
    }
}