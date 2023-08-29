public class UnmarkCommand implements Command {
    private final int taskIndex;

    public UnmarkCommand(String input) throws SallyException {
        try {
            taskIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to unmark.");
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws SallyException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new SallyException("OOPS! Provide a valid task number to unmark.");
        }

        Task task = tasks.getTask(taskIndex);
        task.unmark();
        storage.saveTasksToFile(tasks);
        ui.showUnmarkedTask(task);
    }
}
