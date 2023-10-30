package rocket;

public class ListCommand extends Command{
    public ListCommand() {
        super(false);
    }

    /**
     * Execute list command
     * @param tasks Modifies or uses the tasklist if needed.
     * @param ui Displays to the UI if needed.
     * @param storage Stores and retrieves data from the storage.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String response = "Your tasks are: \n";
        response += tasklistToString(tasks);

        System.out.println(response);
        ui.setLastResponse(response);
    }

    private String tasklistToString(TaskList tasks) {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += "    " + (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return result;
    }

}
