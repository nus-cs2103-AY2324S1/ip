public class DeleteCommand extends Command{
    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage){
        try {
            tasks.deleteTask(taskNumber);
            ui.deleteTaskResponse(tasks.getTask(taskNumber), tasks);
            storage.saveList(tasks);
        } catch (ChatException e) {
            ui.showLoadingError(e);
        }
    };
    public boolean isExit() {
        return false;
    };
}
