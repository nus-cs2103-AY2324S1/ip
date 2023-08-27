public class MarkAsUndoneCommand extends Command {
    private final int taskIndex;

    public MarkAsUndoneCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.markTaskAsUndone(taskIndex);
            ui.showTaskMarkedAsUndone(tasks, taskIndex);
        } else {
            throw new DukeException("Invalid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
