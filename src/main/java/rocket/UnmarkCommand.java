package rocket;

public class UnmarkCommand extends Command {
    private int taskNumber;

    /**
     * Create unmark command.
     * @param taskNumber task to be unmarked.
     */
    public UnmarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * executes unmark command.
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber);
        task.markAsUndone();
        System.out.println("    OK, I've marked this task as not done yet:");
        System.out.println("      " + task);
    }
}
