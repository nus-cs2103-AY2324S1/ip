public class UnmarkCommand extends Command {
    private int taskIndexToUnmark;

    public UnmarkCommand(int taskIndexToUnmark) {
        this.taskIndexToUnmark = taskIndexToUnmark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndexToUnmark <= 0 || taskIndexToUnmark > tasks.size()) {
            throw new DukeInvalidIndexException(taskIndexToUnmark);
        }

        Task taskToUnmark = tasks.get(taskIndexToUnmark - 1);
        taskToUnmark.unmark();

        ui.displayUnmarkedTask(taskToUnmark);

        storage.saveTasks(tasks);
    }
}