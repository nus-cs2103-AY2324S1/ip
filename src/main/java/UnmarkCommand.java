public class UnmarkCommand extends Command {
    private int taskNum;
    public UnmarkCommand(int taskNum) {
        super(false);
        this.taskNum = taskNum;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.markUndone(taskNum);
    }
}
