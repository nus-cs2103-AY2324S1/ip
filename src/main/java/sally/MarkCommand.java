package sally;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand implements Command {

    private final int taskIndex;

    /**
     * Constructs a MarkCommand with the specified task index to mark.
     *
     * @param input The user input indicating the task index to mark.
     * @throws SallyException If the input task index is invalid.
     */
    public MarkCommand(String input) throws SallyException {
        try {
            taskIndex = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number to mark.");
        }
    }

    /**
     * Executes the mark command on the specified TaskList, Storage, and Ui.
     * Marks the task at the specified index as done.
     *
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @throws SallyException If the specified task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new SallyException("OOPS! Provide a valid task number to mark.");
        }

        Task task = tasks.getTask(taskIndex);
        task.mark();
        storage.saveTasksToFile(tasks);
        //edit String below
        String res = "Nice! I've marked this task as done:\n" + task.toString();
        return res;
    }
}
