public class UnmarkCommand extends Command {
    int taskNumber;
    public UnmarkCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markUndone(taskNumber);
            ui.markUndoneResponse(tasks.getTask(taskNumber));
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    public boolean isExit() {
        return false;
    };
}
