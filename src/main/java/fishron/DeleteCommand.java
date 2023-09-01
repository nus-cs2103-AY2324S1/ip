package fishron;

public class DeleteCommand extends Command{
    private int taskNum;
    public DeleteCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(taskNum);
        ui.showTaskDeleted(taskList, taskList.getTask(taskNum));
    }
}