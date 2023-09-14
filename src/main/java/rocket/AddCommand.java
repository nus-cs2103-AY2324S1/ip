package rocket;

public class AddCommand extends Command{
    private Task task;
    public AddCommand (Task task) {
        super(false);
        this.task = task;
    }

    /**
     * Informs the user that the task has been added, and tells them how many tasks are in the list
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays data to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        // Add task to task list
        tasks.add(this.task);

        // Create response
        String response = "";
        response += "    Got it. I've added this task:\n";
        response += "      " + task + "\n";
        response += "    Now you have " + tasks.size() + " tasks in the list";

        // Send response to command line and the UI
        System.out.println(response);
        ui.setLastResponse(response);
    }
}
