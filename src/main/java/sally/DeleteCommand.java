package sally;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;
    private Message message;

    /**
     * Constructs a DeleteCommand object with the task index to be deleted.
     *
     * @param input The input string representing the task index to be deleted.
     * @throws SallyException If the input is not a valid task index.
     */
    public DeleteCommand(String input) throws SallyException {
        try {
            taskIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to delete.");
        }
    }

    /**
     * Executes the delete command by removing the specified task from the task list,
     * saving the updated task list to storage, and displaying the deleted task.
     *
     * @param tasks The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @return A string indicating the task has been deleted.
     * @throws SallyException If the specified task index is invalid or there's an issue
     *                        while deleting the task.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        message = new Message();

        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new SallyException("OOPS! Provide a valid task number to delete.");
        }

        Task deletedTask = tasks.getTask(taskIndex);
        tasks.deleteTask(taskIndex);
        storage.saveTasksToFile(tasks);
        return message.deleteMessage(deletedTask, tasks.getSize());
    }
}
