public class MarkDoneCommand extends Command {
    private int taskNum;
    public MarkDoneCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markAsDone(taskNum);
        ui.showDoneTask(taskList.getTask(taskNum));
    }
}