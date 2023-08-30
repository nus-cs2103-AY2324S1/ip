public class DeleteCommand extends Command{
    private int taskNum;
    public DeleteCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        tasks.delete(taskNum);
        ui.showLine();
    }
}
