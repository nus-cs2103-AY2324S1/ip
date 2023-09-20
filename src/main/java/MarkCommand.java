public class MarkCommand extends Command {
    private int taskIndexToMark;

    public MarkCommand(int taskIndexToMark) {
        this.taskIndexToMark = taskIndexToMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndexToMark <= 0 || taskIndexToMark > tasks.size()) {
            throw new DukeInvalidIndexException(taskIndexToMark);
        }

        Task taskToMark = tasks.get(taskIndexToMark - 1);
        taskToMark.mark();

        ui.displayMarkedTask(taskToMark);

        storage.saveTasks(tasks);
    }
}