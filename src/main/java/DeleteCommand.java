public class DeleteCommand extends Command {

    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws DidierException {
        Task task = taskList.removeTask(taskNumber);
        ui.botPrintTaskRemoved(task, taskList.getSize());
        storage.saveTasks(taskList);
    }
}
