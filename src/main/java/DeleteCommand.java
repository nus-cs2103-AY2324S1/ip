public class DeleteCommand extends Command {
    private int taskIndexToDelete;

    public DeleteCommand(int taskIndexToDelete) {
        this.taskIndexToDelete = taskIndexToDelete;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (taskIndexToDelete <= 0 || taskIndexToDelete > tasks.size()) {
            throw new DukeInvalidIndexException(taskIndexToDelete);
        }

        Task deletedTask = tasks.get(taskIndexToDelete - 1); // Lists are 0-indexed, but users see a 1-indexed list.
        tasks.remove(taskIndexToDelete - 1);

        ui.displayDeletedTask(deletedTask, tasks);

        storage.saveTasks(tasks);
    }
}
