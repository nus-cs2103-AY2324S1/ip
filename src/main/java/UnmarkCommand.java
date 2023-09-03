public class UnmarkCommand extends Command {
    int taskNumber;

    UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        Task task = tasks.get(this.taskNumber);
        task.unmarkDone();
        ui.showUnmarkText(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
