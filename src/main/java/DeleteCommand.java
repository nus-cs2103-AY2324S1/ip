public class DeleteCommand extends Command {
    private final int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.deleteTask(taskIndex);
            ui.showTaskRemoved(tasks, removedTask);
        } else {
            throw new DukeException("Invalid task number.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
