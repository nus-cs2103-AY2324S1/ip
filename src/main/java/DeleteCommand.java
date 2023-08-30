/**
 * Command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String details;

    /**
     * Instantiates a delete command.
     * @param details Task number to be deleted.
     */
    public DeleteCommand(String details) {
        super(false);
        this.details = details;
    }

    /**
     * Deletes a task using the task number.
     * @param tasks ArrayList of tasks.
     * @param ui User interaction handler.
     * @param storage File loading and saving handler.
     * @throws OscarException Failure to validate task number.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws OscarException {
        int index = validateInt(details, tasks);
        String currentTask = tasks.delete(index);
        storage.save(tasks);
        System.out.println("Oscar has removed this task:\n");
        System.out.println(currentTask + "\n");
        tasks.listCount();
    }
}
