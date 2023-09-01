package rocket;

public class DeleteCommand extends Command{
    public DeleteCommand() {
        super(false);
    }

    private int taskNumber;

    /**
     * Creates delete command
     * @param taskNumber the task number.
     */
    public DeleteCommand(int taskNumber) {
        super(false);
        this.taskNumber = taskNumber;
    }

    /**
     * executes delete command
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.get(taskNumber);
        tasks.remove(taskNumber);
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list");
    }


}
