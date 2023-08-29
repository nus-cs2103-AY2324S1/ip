public class MarkCommand implements Command {

    private final int taskIndex;

    public MarkCommand(String input) throws SallyException {
        try {
            taskIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to mark.");
        }
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws SallyException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new SallyException("OOPS! Provide a valid task number to mark.");
        }

        Task task = tasks.getTask(taskIndex);
        task.mark();
        storage.saveTasksToFile(tasks);
        ui.showMarkedTask(task);
    }
}
