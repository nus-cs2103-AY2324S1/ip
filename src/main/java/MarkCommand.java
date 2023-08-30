public class MarkCommand extends Command {
    int taskIndex;

    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws BongoException {
        if (tasks.getTotalTasks() == 0) throw new BongoException("There are currently no tasks.");
        if (taskIndex < 0 || taskIndex >= tasks.getTotalTasks()) throw new BongoException("Task does not exist.");
        tasks.markTaskDone(this.taskIndex);
        ui.showTaskIsDone(tasks.getTask(this.taskIndex));
        storage.edit(Bongo.FileAction.MARK_TASK, this.taskIndex + 1);
    }
}
