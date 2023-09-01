public class UnmarkCommand extends Command {
    private int taskNum;
    public UnmarkCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsUndone(taskNum);
        ui.showUnmarkTask(taskList.getTask(taskNum));
    }
}