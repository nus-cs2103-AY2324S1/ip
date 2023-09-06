package sally;

/**
 * Represents a command to unmark a task as done.
 */
public class UnmarkCommand implements Command {
    private final int taskIndex;
    private Message message;

    /**
     * Constructs an UnmarkCommand object with the provided input.
     *
     * @param input The input representing the task index to unmark.
     * @throws SallyException If the input is not a valid task index.
     */
    public UnmarkCommand(String input) throws SallyException {
        try {
            taskIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to unmark.");
        }
    }

    /**
     * Executes the unmark command on the specified task, updating its status as not done.
     * Updates the task list and saves it to the storage.
     *
     * @param tasks The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @throws SallyException If the task index is invalid or an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        message = new Message();

        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new SallyException("OOPS! Provide a valid task number to unmark.");
        }

        Task task = tasks.getTask(taskIndex);
        task.unmark();
        storage.saveTasksToFile(tasks);
        return message.unmarkMessage(task);
    }
}
