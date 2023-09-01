public class MarkCommand extends Command{
    int taskNumber;
    public MarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(taskNumber);
            ui.markDoneResponse(tasks.getTask(taskNumber));
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    public boolean isExit() {
        return false;
    };
}
