public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.getTask(taskIndex);
        tasks.removeTask(taskIndex);
        ui.showDeleteMessage(tasks.getSize(), task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
