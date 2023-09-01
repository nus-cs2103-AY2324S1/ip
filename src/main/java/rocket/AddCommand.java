package rocket;

public class AddCommand extends Command{
    private Task task;
    public AddCommand (Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Informs the user that task has been added, and tells them how many tasks are in the list
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.add(this.task);
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + task);
        System.out.println("    Now you have " + tasks.size() + " tasks in the list");
    }
}
