package sally;

/**
 * Represents a command to mark a task as done.
 */
public class PriorityCommand implements Command {

    private final int taskIndex;
    private final String priorityString;
    private Message message;

    /**
     * Constructs a PriorityCommand with the specified task index to change the priority of.
     *
     * @param input The user input indicating the task index to change the priority of.
     * @throws SallyException If the input task index is invalid.
     */
    public PriorityCommand(String input) throws SallyException {
        String[] parts = input.split(" ");
        if (parts.length != 2) {
            throw new SallyException("Invalid priority command format. Usage: prioritise [taskIndex] " +
                    "[low/medium/high]");
        }

        try {
            taskIndex = Integer.parseInt(parts[0]) - 1;
        } catch (NumberFormatException e) {
            throw new SallyException("OOPS! Provide a valid task number.");
        }

        this.priorityString = parts[1].toLowerCase();
    }

    /**
     * Executes the prioritise command on the specified TaskList, Storage.
     * Prioritises the task at the specified index.
     *
     * @param tasks   The TaskList containing tasks.
     * @param storage The Storage for tasks.
     * @return A string indicating the task has been prioritised.
     * @throws SallyException If the specified task index is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws SallyException {
        message = new Message();

        if (taskIndex < 0 || taskIndex >= tasks.getSize()) {
            throw new SallyException("OOPS! Provide a valid task number to mark.");
        }

        Task task = tasks.getTask(taskIndex);
        task.setPriority(priorityString);
        storage.saveTasksToFile(tasks);
        return message.prioritiseMessage(task);
    }
}
