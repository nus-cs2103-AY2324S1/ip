public class MarkCommand extends Command{

    private boolean isMark;
    private int taskNumber;
    public MarkCommand(boolean isMark, int taskNumber) {
        this.isMark = isMark;
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList taskList, UI ui, Storage storage) throws TaskNumberException {
        Task task = taskList.getTask(taskNumber);
        if (isMark){
            task.markAsDone();
            ui.botPrintTaskMarkedDone(task, true);
        } else {
            task.markAsNotDone();
            ui.botPrintTaskMarkedDone(task, false);
        }
        storage.saveTasks(taskList);
    }
}
