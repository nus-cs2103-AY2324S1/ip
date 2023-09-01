package rocket;

public class MarkCommand extends Command{
    private int taskNumber;

    /**
     * Creates mark command
     * @param taskNumber task to be marked.
     */
    public MarkCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * Executes mark command.
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber);
        task.markAsDone();
        System.out.println("    Nice! I've marked this task as done:");
        System.out.println("      " + task);
    }
}
