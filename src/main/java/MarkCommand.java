public class MarkCommand extends Command {
    private int taskNumber;

    MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        Task task = tasks.get(this.taskNumber);
        task.markDone();
        ui.showMarkText(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
