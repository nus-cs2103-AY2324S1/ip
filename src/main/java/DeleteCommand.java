public class DeleteCommand implements Command {
    private final int taskIndex;

    public DeleteCommand(String input) throws SallyException {
        try {
            taskIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to delete.");
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws SallyException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new SallyException("OOPS! Provide a valid task number to delete.");
        }

        Task deletedTask = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        storage.saveTasksToFile(tasks);
        ui.showDeletedTask(deletedTask, tasks.getSize());
    }
}
